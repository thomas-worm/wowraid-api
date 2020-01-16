package de.thomasworm.wowraid.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.thomasworm.wowraid.api.model.persistence.Event;
import de.thomasworm.wowraid.api.model.persistence.EventRepository;
import reactor.core.publisher.Mono;

@Service()
public class EventService {

    private EventRepository eventRepository;

    @Autowired()
    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public Mono<Iterable<Event>> getAll() {
        return Mono.just(this.eventRepository.findAll());
    }

}