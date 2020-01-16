package de.thomasworm.wowraid.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.thomasworm.wowraid.api.model.persistence.Eventcategory;
import de.thomasworm.wowraid.api.model.persistence.EventcategoryRepository;
import reactor.core.publisher.Mono;

@Service()
public class EventcategoryService {

    private EventcategoryRepository eventcategoryRepository;

    @Autowired()
    public EventcategoryService(EventcategoryRepository eventcategoryRepository) {
        this.eventcategoryRepository = eventcategoryRepository;
    }

    public Mono<Iterable<Eventcategory>> getAll() {
        return Mono.just(this.eventcategoryRepository.findAll());
    }

    public Mono<Eventcategory> getByName(String name) {
        return Mono.just(this.eventcategoryRepository.findByName(name));
    }

}