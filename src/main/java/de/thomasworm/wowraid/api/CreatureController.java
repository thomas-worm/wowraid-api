package de.thomasworm.wowraid.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import de.thomasworm.wowraid.api.model.dto.Creature;
import reactor.core.publisher.Mono;

@RestController()
public class CreatureController {

    private CreatureService creatureService;

    public CreatureController(CreatureService creatureService) {
        this.creatureService = creatureService;
    }

    @GetMapping("/creature")
    public Mono<Iterable<Creature>> getCreatures() {
        return this.creatureService.getAll().map(creatures -> {
            List<Creature> creatureList = new ArrayList<>();
            creatures.forEach(creatureRecord -> {
                Creature creature = new Creature();
                creature.setName(creatureRecord.getName());
                creature.setBlizzardIdentifier(creatureRecord.getBlizzardIdentifier());
                creatureList.add(creature);
            });
            return creatureList;
        });
    }

}