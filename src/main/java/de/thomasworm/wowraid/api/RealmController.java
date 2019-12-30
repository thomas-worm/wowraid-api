package de.thomasworm.wowraid.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@RestController()
class RealmController {

    private RealmService realmService;

    @Autowired()
    public RealmController(RealmService realmService) {
        this.realmService = realmService;
    }

    @GetMapping("/realm")
    public Mono<List<String>> getRealms() {
        return this.realmService.getAll().map(realmRecords -> {
            List<String> realms = new ArrayList<>();
            realmRecords.forEach(realm -> {
                realms.add(realm.getName());
            });
            return realms;
        });
    }

}