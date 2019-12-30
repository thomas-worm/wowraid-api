package de.thomasworm.wowraid.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.thomasworm.wowraid.api.model.persistence.Faction;
import de.thomasworm.wowraid.api.model.persistence.FactionRepository;
import reactor.core.publisher.Mono;

@Service()
public class FactionService {

    private FactionRepository factionRepository;

    @Autowired()
    public FactionService(FactionRepository factionRepository) {
        this.factionRepository = factionRepository;
    }

    public Mono<Iterable<Faction>> getAll() {
        return Mono.just(this.factionRepository.findAll());
    }

    public Mono<Faction> getByName(String name) {
        return Mono.just(this.factionRepository.findByName(name));
    }

}