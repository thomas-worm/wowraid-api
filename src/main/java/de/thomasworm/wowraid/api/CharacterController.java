package de.thomasworm.wowraid.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import de.thomasworm.wowraid.api.model.dto.Character;
import de.thomasworm.wowraid.api.model.dto.UserInfo;
import de.thomasworm.wowraid.api.model.persistence.CharacterRepository;
import de.thomasworm.wowraid.api.model.persistence.User;
import de.thomasworm.wowraid.api.model.persistence.UserRepository;
import reactor.core.publisher.Mono;

@RestController()
class CharacterController {

    private CharacterService characterService;
    private UserRepository userRepository;

    @Autowired()
    public CharacterController(CharacterService characterService, UserRepository userRepository) {
        this.characterService = characterService;
        this.userRepository = userRepository;
    }

    @GetMapping("/character")
    public Mono<List<Character>> getCharacters() {
        return Mono.create(subscriber -> 
            this.characterService.getAll().subscribe(characterRecords -> {
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
                subscriber.success(characters);
            })
        );
    }

    @GetMapping("/user/character")
    public Mono<List<Character>> getUsersCharacters(OAuth2AuthenticationToken token) {
        return Mono.create(subscriber -> 
            this.characterService.getCharactersByUser(getUserFromToken(token)).subscribe(characterRecords -> {
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
                subscriber.success(characters);
            })
        );
    }

    private User getUserFromToken(OAuth2AuthenticationToken token) {
        UserInfo userInfo = new UserInfo(token);
        User user = new User();
        user.setBlizzardIdentifier(userInfo.getUserIdentifier());
        user.setBattleTag(userInfo.getBattleTag());
        this.userRepository.addOrUpdateByBlizzardIdentifier(user);
        return user;
    }

}