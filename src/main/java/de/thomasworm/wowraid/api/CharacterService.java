package de.thomasworm.wowraid.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.thomasworm.wowraid.api.model.persistence.Character;
import de.thomasworm.wowraid.api.model.persistence.CharacterRepository;
import de.thomasworm.wowraid.api.model.persistence.User;
import reactor.core.publisher.Mono;

@Service()
public class CharacterService {

    private CharacterRepository characterRepository;

    @Autowired()
    public CharacterService(CharacterRepository characterRepository) {
        this.characterRepository = characterRepository;
    }

    public Mono<Iterable<Character>> getAll() {
        return Mono.just(this.characterRepository.findAll());
    }

    public Mono<Iterable<Character>> getCharactersByUser(User user) {
        return Mono.just(this.characterRepository.findByUser(user));
    }
}