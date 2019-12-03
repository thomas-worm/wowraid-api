package de.thomasworm.wowraid.api;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.internal.util.type.PrimitiveWrapperHelper.CharacterDescriptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import de.thomasworm.wowraid.api.model.dto.Character;
import de.thomasworm.wowraid.api.model.persistence.CharacterRepository;
import reactor.core.publisher.Mono;

@RestController()
class CharacterController {

    private CharacterRepository characterRepository;

    @Autowired()
    public CharacterController(CharacterRepository characterRepository) {
        this.characterRepository = characterRepository;
    }

    @GetMapping("/character")
    public Mono<List<Character>> getCharacters() {
        List<Character> characters = new ArrayList<>();
        this.characterRepository.findAll().forEach(character -> {
            Character characterDto = new Character();
            characterDto.setRealm(character.getRealm().getName());
            characterDto.setName(character.getName());
            characterDto.setFaction(character.getFaction().getName());
            characterDto.setRace(character.getRace().getName());
            characterDto.setCharacterClass(character.getCharacterClass().getName());
            characters.add(characterDto);
        });
        return Mono.just(characters);
    }

}