package de.thomasworm.wowraid.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.thomasworm.wowraid.api.model.persistence.Creature;
import de.thomasworm.wowraid.api.model.persistence.CreatureRepository;
import reactor.core.publisher.Mono;

@Service()
public class CreatureService {

    private CreatureRepository creatureRepository;

    @Autowired()
    public CreatureService(CreatureRepository creatureRepository) {
        this.creatureRepository = creatureRepository;
    }

    public Mono<Iterable<Creature>> getAll() {
        return Mono.justOrEmpty(this.creatureRepository.findAll());
    }

    public Mono<Creature> getByBlizzardIdentifier(int blizzardIdentifier) {
        return Mono.justOrEmpty(this.creatureRepository.findByBlizzardIdentifier(blizzardIdentifier));
    }

}