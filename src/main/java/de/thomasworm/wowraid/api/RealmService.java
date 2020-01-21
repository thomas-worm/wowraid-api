package de.thomasworm.wowraid.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.thomasworm.wowraid.api.model.persistence.Realm;
import de.thomasworm.wowraid.api.model.persistence.RealmRepository;
import reactor.core.publisher.Mono;

@Service()
public class RealmService {

    private RealmRepository realmRepository;

    @Autowired()
    public RealmService(RealmRepository realmRepository) {
        this.realmRepository = realmRepository;
    }

    public Mono<Iterable<Realm>> getAll() {
        return Mono.just(this.realmRepository.findAll());
    }

    public Mono<Realm> getByName(String name) {
        return Mono.just(this.realmRepository.findByNameIgnoreCase(name));
    }

}