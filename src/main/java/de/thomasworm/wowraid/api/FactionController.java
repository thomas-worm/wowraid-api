package de.thomasworm.wowraid.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@RestController()
class FactionController {

    private FactionService factionService;

    @Autowired()
    public FactionController(FactionService factionService) {
        this.factionService = factionService;
    }

    @GetMapping("/faction")
    public Mono<List<String>> getFactions() {
        return this.factionService.getAll().map(factionRecords -> {
            List<String> factions = new ArrayList<>();
            factionRecords.forEach(faction -> {
                factions.add(faction.getName());
            });
            return factions;
        });
    }

}