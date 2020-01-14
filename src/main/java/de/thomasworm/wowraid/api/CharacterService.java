package de.thomasworm.wowraid.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.thomasworm.wowraid.api.model.persistence.Character;
import de.thomasworm.wowraid.api.model.persistence.CharacterRepository;
import de.thomasworm.wowraid.api.model.persistence.DuplicateKeyException;
import de.thomasworm.wowraid.api.model.persistence.Realm;
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

    public Mono<Character> getByRealmAndName(Realm realm, String name) {
        return Mono.just(this.characterRepository.findByRealmAndName(realm, name));
    }

    public void create(Character character) throws DuplicateKeyException {
        Character existingCharacter = this.characterRepository.findByRealmAndName(character.getRealm(), character.getName());
        if (existingCharacter != null)
            throw DuplicateKeyException.withExistingRecord(existingCharacter);
        this.characterRepository.add(character);
    }

    public Character createOrLink(Character character) throws DuplicateKeyException {
        try {
            this.create(character);
            return character;
        } catch (DuplicateKeyException exception) {
            Character existingCharacter = exception.getExistingRecord(Character.class);
            if (existingCharacter.getUser() != null) {
                throw exception;
            }
            if (!existingCharacter.getFaction().getId().equals(character.getFaction().getId())) {
                throw exception;
            }
            if (!existingCharacter.getRace().getId().equals(character.getRace().getId())) {
                throw exception;
            }
            if (!existingCharacter.getCharacterClass().getId().equals(character.getCharacterClass().getId())) {
                throw exception;
            }
            existingCharacter.setUser(character.getUser());
            return this.characterRepository.save(existingCharacter);
        }
    }
}