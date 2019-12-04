package de.thomasworm.wowraid.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.thomasworm.wowraid.api.model.persistence.Faction;
import de.thomasworm.wowraid.api.model.persistence.Race;
import de.thomasworm.wowraid.api.model.persistence.RaceRepository;
import reactor.core.publisher.Mono;

@Service()
public class RaceService {

    private RaceRepository raceRepository;

    @Autowired()
    public RaceService(RaceRepository raceRepository) {
        this.raceRepository = raceRepository;
    }

    public Mono<Iterable<Race>> getAll() {
        return Mono.just(this.raceRepository.findAll());
    }

    public Mono<Iterable<Race>> getByFaction(Faction faction) {
        String[] allianceRaces = new String[] { "Human", "Dwarf", "Night Elf", "Gnome" };
        String[] hordeRaces = new String[] { "Orc", "Undead", "Tauren", "Troll" };
        List<String> relevantRaces = Arrays.asList(faction.getName().equals("Alliance")
            ? allianceRaces
            : (faction.getName().equals("Horde")
                ? hordeRaces
                : new String[]{}));
        return Mono.create(subscriber -> 
            this.getAll().subscribe(races -> {
                List<Race> filteredRaces = new ArrayList<>();
                for (Race race : races) {
                    if (relevantRaces.contains(race.getName()))
                        filteredRaces.add(race);
                }
                subscriber.success(filteredRaces);
            })
        );
    }

}