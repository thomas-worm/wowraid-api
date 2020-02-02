package de.thomasworm.wowraid.api;

import org.springframework.stereotype.Service;

import de.thomasworm.wowraid.api.model.persistence.CharacterRole;
import de.thomasworm.wowraid.api.model.persistence.CharacterRoleRepository;
import reactor.core.publisher.Mono;

@Service()
public class CharacterRoleService {

    private CharacterRoleRepository characterRoleRepository;

    public CharacterRoleService(CharacterRoleRepository characterRoleRepository) {
        this.characterRoleRepository = characterRoleRepository;
    }

    public Mono<CharacterRole> findByName(String name) {
        return Mono.justOrEmpty(this.characterRoleRepository.findByName(name));
    }

}