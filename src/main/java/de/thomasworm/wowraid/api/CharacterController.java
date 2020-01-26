package de.thomasworm.wowraid.api;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import de.thomasworm.wowraid.api.model.dto.Character;
import de.thomasworm.wowraid.api.model.dto.UnprocessableEntityError;
import de.thomasworm.wowraid.api.model.persistence.CharacterClass;
import de.thomasworm.wowraid.api.model.persistence.DuplicateKeyException;
import de.thomasworm.wowraid.api.model.persistence.Faction;
import de.thomasworm.wowraid.api.model.persistence.Race;
import de.thomasworm.wowraid.api.model.persistence.Realm;
import de.thomasworm.wowraid.api.model.persistence.User;
import reactor.core.publisher.Mono;

@RestController()
class CharacterController {

    private CharacterService characterService;
    private UserService userService;
    private CharacterClassService characterClassService;
    private RaceService raceService;
    private FactionService factionService;
    private RealmService realmService;

    @Autowired()
    public CharacterController(CharacterService characterService, UserService userService,
                               CharacterClassService characterClassService, RaceService raceService,
                               FactionService factionService, RealmService realmService) {
        this.characterService = characterService;
        this.userService = userService;
        this.characterClassService = characterClassService;
        this.raceService = raceService;
        this.factionService = factionService;
        this.realmService = realmService;
    }

    @GetMapping("/character")
    public Mono<List<Character>> getCharacters() {
        return this.characterService.getAll().map(characterRecords -> {
            List<Character> characters = new ArrayList<>();
            characterRecords.forEach(character -> {
                Character characterDto = new Character();
                Realm realm = character.getRealm();
                if (realm != null)
                    characterDto.setRealm(realm.getName());
                characterDto.setName(character.getName());
                Faction faction = character.getFaction();
                if (faction != null)
                    characterDto.setFaction(faction.getName());
                Race race = character.getRace();
                if (race != null)
                    characterDto.setRace(race.getName());
                CharacterClass characterClass = character.getCharacterClass();
                if (characterClass != null)
                    characterDto.setCharacterClass(characterClass.getName());
                characters.add(characterDto);
            });
            return characters;
        });
    }

    @GetMapping("/user/character")
    public Mono<List<Character>> getUsersCharacters(OAuth2AuthenticationToken token) {
        return this.characterService.getCharactersByUser(getUserFromToken(token)).map(characterRecords -> {
            List<Character> characters = new ArrayList<>();
            characterRecords.forEach(character -> {
                Character characterDto = new Character();
                characterDto.setRealm(character.getRealm().getName());
                characterDto.setName(character.getName());
                characterDto.setFaction(character.getFaction().getName());
                characterDto.setRace(character.getRace().getName());
                characterDto.setCharacterClass(character.getCharacterClass().getName());
                characters.add(characterDto);
            });
            return characters;
        });
    }

    @GetMapping("/realm/{realm}/character/{name}")
    public Mono<Character> getUserByRealmAndName(@PathVariable("realm") String realm, @PathVariable("name") String name) {
        return this.realmService.getByName(realm)
            .flatMap(realmObject -> this.characterService.getByRealmAndName(realmObject, name))
            .map(characterRecord -> {
                Character character = new Character();
                character.setName(characterRecord.getName());
                character.setRealm(characterRecord.getRealm().getName());
                character.setFaction(characterRecord.getFaction().getName());
                character.setRace(characterRecord.getRace().getName());
                character.setCharacterClass(characterRecord.getCharacterClass().getName());
                return character;
            });
    }

    @PostMapping("/user/character")
    public Mono<ResponseEntity<Object>> createCharacter(@RequestBody() Character character, OAuth2AuthenticationToken token) {
        if (isNullOrBlank(character.getRealm())) {
            return unprocessableEntity("realm", "empty", "The realm is empty but required for the character.");
        }
        if (isNullOrBlank(character.getFaction())) {
            return unprocessableEntity("faction", "empty", "The faction is empty but required for the character.");
        }
        if (isNullOrBlank(character.getRace())) {
            return unprocessableEntity("race", "empty", "The race is empty but required for the character.");
        }
        if (isNullOrBlank(character.getCharacterClass())) {
            return unprocessableEntity("class", "empty", "The class is empty but required for the character.");
        }
        if (isNullOrBlank(character.getName())) {
            return unprocessableEntity("name", "empty", "The name is empty but required for the character.");
        }
        User user = getUserFromToken(token);
        Mono<CharacterClass> characterClassMono = this.characterClassService.getByName(character.getCharacterClass());
        Mono<Race> raceMono = this.raceService.getByName(character.getRace());
        Mono<Faction> factionMono = this.factionService.getByName(character.getFaction());
        Mono<Realm> realmMono = this.realmService.getByName(character.getRealm());
        return Mono.zip(characterClassMono, raceMono, factionMono, realmMono).map(tuple -> {
            de.thomasworm.wowraid.api.model.persistence.Character characterRecord = new de.thomasworm.wowraid.api.model.persistence.Character();
            characterRecord.setUser(user);
            characterRecord.setCharacterClass((CharacterClass) tuple.get(0));
            characterRecord.setRace((Race) tuple.get(1));
            characterRecord.setFaction((Faction) tuple.get(2));
            characterRecord.setRealm((Realm) tuple.get(3));
            characterRecord.setName(character.getName());
            return characterRecord;
        }).flatMap(characterRecord -> {
            if (characterRecord.getRealm() == null) {
                return unprocessableEntity("realm", "notFound", "The given realm was not found.");
            }
            if (characterRecord.getFaction() == null) {
                return unprocessableEntity("faction", "notFound", "The given faction was not found.");
            }
            if (characterRecord.getRace() == null) {
                return unprocessableEntity("race", "notFound", "The given race was not found.");
            }
            if (characterRecord.getCharacterClass() == null) {
                return unprocessableEntity("class", "notFound", "The given class was not found.");
            }
            URI createdResourceUri = null;
            try {
                createdResourceUri = new URI("/realm/" + characterRecord.getRealm().getName() + "/character/" + characterRecord.getName());
            } catch (URISyntaxException exception) {}
            try {
                this.characterService.createOrLink(characterRecord);
            } catch (DuplicateKeyException exception) {
                return Mono.just(ResponseEntity
                    .status(HttpStatus.SEE_OTHER)
                    .header(HttpHeaders.LOCATION, createdResourceUri.toString())
                    .build());
            }
            return Mono.just(ResponseEntity
                    .status(HttpStatus.CREATED)
                    .header(HttpHeaders.LOCATION, createdResourceUri.toString())
                    .build());
        });
    }

    private User getUserFromToken(OAuth2AuthenticationToken token) {
        User user = this.userService.getUserByToken(token);
        return user;
    }

    private boolean isNullOrBlank(String string) {
        return string == null || string.trim().isEmpty();
    }

    private Mono<ResponseEntity<Object>> unprocessableEntity(String path, String error, String errorMessage) {
        return Mono.just(ResponseEntity
            .status(HttpStatus.UNPROCESSABLE_ENTITY)
            .body(new UnprocessableEntityError(path, error, errorMessage))
        );
    }

}