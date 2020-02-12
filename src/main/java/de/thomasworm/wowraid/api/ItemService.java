package de.thomasworm.wowraid.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.thomasworm.wowraid.api.model.persistence.Item;
import de.thomasworm.wowraid.api.model.persistence.ItemRepository;
import reactor.core.publisher.Mono;

@Service()
public class ItemService {

    private ItemRepository itemRepository;

    @Autowired()
    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public Mono<Iterable<Item>> getAll() {
        return Mono.justOrEmpty(itemRepository.findAll());
    }

    public Mono<Item> getByBlizzardIdentifier(int blizzardIdentifier) {
        return Mono.justOrEmpty(itemRepository.findByBlizzardIdentifier(blizzardIdentifier));
    }

}