package de.thomasworm.wowraid.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import de.thomasworm.wowraid.api.model.persistence.FactionRepository;
import reactor.core.publisher.Mono;

@RestController()
class FactionController {

    private FactionRepository factionRepository;

    @Autowired()
    public FactionController(FactionRepository factionRepository) {
        this.factionRepository = factionRepository;
    }

    @GetMapping("/faction")
    public Mono<List<String>> getFactions() {
        List<String> factions = new ArrayList<>();
        this.factionRepository.findAll().forEach(faction -> {
            factions.add(faction.getName());
        });
        return Mono.just(factions);
    }

}