package de.thomasworm.wowraid.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import de.thomasworm.wowraid.api.model.persistence.Faction;
import de.thomasworm.wowraid.api.model.persistence.FactionRepository;
import de.thomasworm.wowraid.api.model.persistence.Race;
import de.thomasworm.wowraid.api.model.persistence.RaceRepository;
import reactor.core.publisher.Mono;

@RestController()
class RaceController {

    private RaceService raceService;
    private FactionRepository factionRepository;

    @Autowired()
    public RaceController(RaceService raceService, FactionRepository factionRepository) {
        this.raceService = raceService;
        this.factionRepository = factionRepository;
    }

    @GetMapping("/race")
    public Mono<List<String>> getRaces() {
        return Mono.create(subscriber -> {
            this.raceService.getAll().subscribe(races -> {
                subscriber.success(mapRaces(races));
            });
        });
    }

    @GetMapping("/faction/alliance/race")
    public Mono<List<String>> getAllianceRaces() {
        return getFactionRaces("Alliance");
    }

    @GetMapping("/faction/horde/race")
    public Mono<List<String>> getHordeRaces() {
        return getFactionRaces("Horde");
    }

    private Mono<List<String>> getFactionRaces(String factionName) {
        return Mono.create(subscriber -> {
            Faction faction = this.factionRepository.findByName(factionName);
            this.raceService.getByFaction(faction).subscribe(races -> {
                subscriber.success(mapRaces(races));
            });
        });
    }

    private List<String> mapRaces(Iterable<Race> races) {
        List<String> mappedRaces = new ArrayList<>();
        races.forEach(race -> {
            mappedRaces.add(race.getName());
        });
        return mappedRaces;
    }

}