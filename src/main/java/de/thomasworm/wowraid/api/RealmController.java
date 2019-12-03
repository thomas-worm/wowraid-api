package de.thomasworm.wowraid.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import de.thomasworm.wowraid.api.model.persistence.RealmRepository;
import reactor.core.publisher.Mono;

@RestController()
class RealmController {

    private RealmRepository realmRepository;

    @Autowired()
    public RealmController(RealmRepository realmRepository) {
        this.realmRepository = realmRepository;
    }

    @GetMapping("/realm")
    public Mono<List<String>> getRealms() {
        List<String> realms = new ArrayList<>();
        this.realmRepository.findAll().forEach(realm -> {
            realms.add(realm.getName());
        });
        return Mono.just(realms);
    }

}