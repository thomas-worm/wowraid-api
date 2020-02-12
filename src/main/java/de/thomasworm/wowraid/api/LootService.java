package de.thomasworm.wowraid.api;

import org.springframework.stereotype.Service;

import de.thomasworm.wowraid.api.model.persistence.Character;
import de.thomasworm.wowraid.api.model.persistence.Creature;
import de.thomasworm.wowraid.api.model.persistence.Drop;
import de.thomasworm.wowraid.api.model.persistence.DropRepository;
import de.thomasworm.wowraid.api.model.persistence.Event;
import de.thomasworm.wowraid.api.model.persistence.EventRepository;
import de.thomasworm.wowraid.api.model.persistence.Item;

@Service()
public class LootService {

    private DropRepository dropRepository;
    private EventRepository eventRepository;

    public LootService(DropRepository dropRepository, EventRepository eventRepository) {
        this.dropRepository = dropRepository;
        this.eventRepository = eventRepository;
    }

    public void createLoot(Item item, Creature creature, Event event, Character character) {
        Drop drop = new Drop();
        drop.setItem(item);
        drop.setCreature(creature);
        drop.setLooter(character);
        drop = this.dropRepository.save(drop);
        if (event != null) {
            event.getDrops().add(drop);
            event = this.eventRepository.save(event);
        }
    }

}