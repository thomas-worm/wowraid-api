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

    @Autowired()
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/event")
    public Mono<List<Event>> getEvents() {
        return this.eventService.getAll().map(eventRecords -> {
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
        });
    }

}