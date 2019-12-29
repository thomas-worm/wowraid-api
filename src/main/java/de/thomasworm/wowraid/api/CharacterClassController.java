package de.thomasworm.wowraid.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import de.thomasworm.wowraid.api.model.persistence.CharacterClass;
import reactor.core.publisher.Mono;

@RestController()
class CharacterClassController {

    private CharacterClassService characterClassService;
    private RaceService raceService;

    @Autowired()
    public CharacterClassController(CharacterClassService characterClassService, RaceService raceService) {
        this.characterClassService = characterClassService;
        this.raceService = raceService;
    }

    @GetMapping("/class")
    public Mono<List<String>> getClasses() {
        return Mono.create(subscriber -> {
            this.characterClassService.getAll().subscribe(classes -> {
                subscriber.success(mapClasses(classes));
            });
        });
    }

    @GetMapping({"/race/human/class",
                 "/race/Human/class"})
    public Mono<List<String>> getHumanClasses() {
        return getRaceClasses("Human");
    }

    @GetMapping({"/race/dwarf/class",
                 "/race/Dwarf/class"})
    public Mono<List<String>> getDwarfClasses() {
        return getRaceClasses("Dwarf");
    }

    @GetMapping({"/race/night elf/class", "/race/nightelf/class",
                 "/race/Night elf/class", "/race/Nightelf/class",
                 "/race/Night Elf/class", "/race/NightElf/class"})
    public Mono<List<String>> getNightElfClasses() {
        return getRaceClasses("Night Elf");
    }

    @GetMapping({"/race/gnome/class",
                 "/race/Gnome/class"})
    public Mono<List<String>> getGnomeClasses() {
        return getRaceClasses("Gnome");
    }

    @GetMapping({"/race/orc/class",
                 "/race/Orc/class"})
    public Mono<List<String>> getOrcClasses() {
        return getRaceClasses("Orc");
    }

    @GetMapping({"/race/undead/class",
                 "/race/Undead/class"})
    public Mono<List<String>> getUndeadClasses() {
        return getRaceClasses("Undead");
    }

    @GetMapping({"/race/tauren/class",
                 "/race/Tauren/class"})
    public Mono<List<String>> getTaurenClasses() {
        return getRaceClasses("Tauren");
    }

    @GetMapping({"/race/troll/class",
                 "/race/Troll/class"})
    public Mono<List<String>> getTrollClasses() {
        return getRaceClasses("Troll");
    }

    private Mono<List<String>> getRaceClasses(String raceName) {
        return Mono.create(subscriber -> {
            this.raceService.getByName(raceName).subscribe(race -> 
                this.characterClassService.getByRace(race).subscribe(classes -> {
                    subscriber.success(mapClasses(classes));
                })
            );
        });
    }

    private List<String> mapClasses(Iterable<CharacterClass> classes) {
        List<String> mappedClasses = new ArrayList<>();
        classes.forEach(race -> {
            mappedClasses.add(race.getName());
        });
        return mappedClasses;
    }

}