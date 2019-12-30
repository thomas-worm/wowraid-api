package de.thomasworm.wowraid.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.thomasworm.wowraid.api.model.persistence.Race;
import de.thomasworm.wowraid.api.model.persistence.CharacterClass;
import de.thomasworm.wowraid.api.model.persistence.CharacterClassRepository;
import reactor.core.publisher.Mono;

@Service()
public class CharacterClassService {

    private CharacterClassRepository characterClassRepository;

    @Autowired()
    public CharacterClassService(CharacterClassRepository characterClassRepository) {
        this.characterClassRepository = characterClassRepository;
    }

    public Mono<Iterable<CharacterClass>> getAll() {
        return Mono.just(this.characterClassRepository.findAll());
    }

    public Mono<CharacterClass> getByName(String className) {
        return Mono.just(this.characterClassRepository.findByName(className));
    }

    public Mono<Iterable<CharacterClass>> getByRace(Race race) {
        List<String> relevantClasses = new ArrayList<>();
        if (race.getName().equals("Human")) {
            relevantClasses = Arrays.asList(new String[] {
                "Priest", "Rogue", "Warrior", "Mage", "Warlock", "Paladin"
            });
        } else if (race.getName().equals("Dwarf")) {
            relevantClasses = Arrays.asList(new String[] {
                "Priest", "Rogue", "Warrior", "Hunter", "Paladin"
            });
        } else if (race.getName().equals("Night Elf")) {
            relevantClasses = Arrays.asList(new String[] {
                "Priest", "Rogue", "Warrior", "Druid", "Hunter"
            });
        } else if (race.getName().equals("Gnome")) {
            relevantClasses = Arrays.asList(new String[] {
                "Rogue", "Warrior", "Mage", "Warlock"
            });
        } else if (race.getName().equals("Orc")) {
            relevantClasses = Arrays.asList(new String[] {
                "Rogue", "Warrior", "Hunter", "Warlock", "Shaman"
            });
        } else if (race.getName().equals("Undead")) {
            relevantClasses = Arrays.asList(new String[] {
                "Priest", "Rogue", "Warrior", "Mage", "Warlock"
            });
        } else if (race.getName().equals("Tauren")) {
            relevantClasses = Arrays.asList(new String[] {
                "Warrior", "Druid", "Hunter", "Shaman"
            });
        } else if (race.getName().equals("Troll")) {
            relevantClasses = Arrays.asList(new String[] {
                "Priest", "Rogue", "Warrior", "Mage", "Hunter", "Shaman"
            });
        }
        List<String> relevantClasses2 = relevantClasses;
        return Mono.create(subscriber -> 
            this.getAll().subscribe(classes -> {
                List<CharacterClass> filteredClasses = new ArrayList<>();
                for (CharacterClass characterClass : classes) {
                    if (relevantClasses2.contains(characterClass.getName()))
                        filteredClasses.add(characterClass);
                }
                subscriber.success(filteredClasses);
            })
        );
    }

}