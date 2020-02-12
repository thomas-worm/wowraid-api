package de.thomasworm.wowraid.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import de.thomasworm.wowraid.api.model.dto.Item;
import reactor.core.publisher.Mono;

@RestController()
public class ItemController {

    private ItemService itemService;

    @Autowired()
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/item")
    public Mono<Iterable<Item>> getItems() {
        return this.itemService.getAll().map(items -> {
            List<Item> itemList = new ArrayList<>();
            items.forEach(itemRecord -> {
                Item item = new Item();
                item.setName(itemRecord.getName());
                item.setBlizzardIdentifier(itemRecord.getBlizzardIdentifier());
                itemList.add(item);
            });
            return itemList;
        });
    }

}