package de.thomasworm.wowraid.api;

import java.util.Arrays;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import de.thomasworm.wowraid.api.model.dto.CreateLootDto;
import de.thomasworm.wowraid.api.model.persistence.Creature;
import de.thomasworm.wowraid.api.model.persistence.Character;
import de.thomasworm.wowraid.api.model.persistence.Event;
import de.thomasworm.wowraid.api.model.persistence.Item;
import de.thomasworm.wowraid.api.model.persistence.User;
import reactor.core.publisher.Mono;

@RestController()
public class LootController {

    private UserService userService;
    private RealmService realmService;
    private CharacterService characterService;
    private EventService eventService;
    private ItemService itemService;
    private CreatureService creatureService;
    private LootService lootService;

    @Autowired()
    public LootController(UserService userService, RealmService realmService, CharacterService characterService, EventService eventService, ItemService itemService, CreatureService creatureService, LootService lootService) {
        this.userService = userService;
        this.realmService = realmService;
        this.characterService = characterService;
        this.eventService = eventService;
        this.itemService = itemService;
        this.creatureService = creatureService;
        this.lootService = lootService;
    }

    @PostMapping("/loot")
    public Mono<ResponseEntity<Object>> createLoot(@RequestBody() CreateLootDto createLootDto, OAuth2AuthenticationToken token) {
        User user = this.userService.getUserByToken(token);
        if (!this.userService.isUserInAnyGroupWithName(user, Arrays.asList(new String[] {"admin"}))) {
            return Mono.just(ResponseEntity.status(HttpStatus.FORBIDDEN).build());
        }
        Event nullEvent = new Event();
        Mono<Event> eventMono = createLootDto.getEventKey() == null ? Mono.just(nullEvent) : this.eventService.getByKey(createLootDto.getEventKey());
        Mono<Item> itemMono = this.itemService.getByBlizzardIdentifier(createLootDto.getItemBlizzardIdentifier());
        Character nullCharacter = new Character();
        Mono<Character> characterMono = 
            (createLootDto.getCharacterName() == null || createLootDto.getCharacterRealm() == null) ? Mono.just(nullCharacter) :
            this.realmService.getByName(createLootDto.getCharacterRealm()).flatMap(realm -> (realm == null) ? Mono.empty() :
                this.characterService.getByRealmAndName(realm, createLootDto.getCharacterName()));
        Creature nullCreature = new Creature();
        Mono<Creature> creatureMono = createLootDto.getCreatureBlizzardIdentifier() == null ? Mono.just(nullCreature) : this.creatureService.getByBlizzardIdentifier(createLootDto.getCreatureBlizzardIdentifier());
        return Mono.zip(eventMono.or(Mono.just(nullEvent)), itemMono, characterMono.or(Mono.just(nullCharacter)), creatureMono.or(Mono.just(nullCreature))).flatMap(tuple -> {
            Event event = (Event) tuple.get(0);
            if (event == nullEvent) event = null;
            Item item = (Item) tuple.get(1);
            Character character = (Character) tuple.get(2);
            if (character == nullCharacter) character = null;
            Creature creature = (Creature) tuple.get(3);
            if (creature == nullCreature) creature = null;

            if (item == null) {
                return Mono.just(ResponseEntity.notFound().build());
            }

            if (createLootDto.getCreatureBlizzardIdentifier() != null && creature == null) {
                return Mono.just(ResponseEntity.notFound().build());
            }

            if (createLootDto.getEventKey() != null && event == null) {
                return Mono.just(ResponseEntity.notFound().build());
            }

            if ((createLootDto.getCharacterName() != null || createLootDto.getCharacterRealm() != null) && character == null) {
                return Mono.just(ResponseEntity.notFound().build());
            }

            this.lootService.createLoot(item, creature, event, character);
            return Mono.just(ResponseEntity
                .status(HttpStatus.CREATED)
                .header(HttpHeaders.LOCATION, "/")
                .build());
        });
    }

}