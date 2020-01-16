package de.thomasworm.wowraid.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import de.thomasworm.wowraid.api.model.dto.Event;
import reactor.core.publisher.Mono;

@RestController()
public class EventController {

    private EventService eventService;
    private EventcategoryService eventcategoryService;

    @Autowired()
    public EventController(EventService eventService, EventcategoryService eventcategoryService) {
        this.eventService = eventService;
        this.eventcategoryService = eventcategoryService;
    }

    @GetMapping("/event")
    public Mono<List<Event>> getEvents() {
        return this.eventService.getAll().map(this::convertEventRecords);
    }

    @GetMapping("/eventcategory/{category}/event")
    public Mono<List<Event>> getEventByCategory(String categoryName) {
        return this.eventcategoryService.getByName(categoryName).flatMap(category -> {
            if (category == null) {
                return Mono.just((List<Event>) new ArrayList<Event>());
            }
            return this.eventService.getByCategory(category).map(this::convertEventRecords);
        });
    }

    private List<Event> convertEventRecords(Iterable<de.thomasworm.wowraid.api.model.persistence.Event> eventRecords) {
        List<Event> events = new ArrayList<>();
        eventRecords.forEach(eventRecord -> {
            Event event = new Event();
            event.setKey(eventRecord.getKey());
            event.setName(eventRecord.getName());
            event.setDescription(eventRecord.getDescription());
            event.setStartDateTime(eventRecord.getStartDateTime());
            event.setFinishDateTime(eventRecord.getFinishDateTime());
            List<String> categories = event.getCategories();
            eventRecord.getCategories().forEach(category -> {
                categories.add(category.getName());
            });
            events.add(event);
        });
        return events;
    }

}