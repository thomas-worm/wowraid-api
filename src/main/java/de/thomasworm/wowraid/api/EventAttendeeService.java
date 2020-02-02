package de.thomasworm.wowraid.api;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import de.thomasworm.wowraid.api.model.persistence.Character;
import de.thomasworm.wowraid.api.model.persistence.CharacterRole;
import de.thomasworm.wowraid.api.model.persistence.Event;
import de.thomasworm.wowraid.api.model.persistence.EventAttendee;

@Service()
public class EventAttendeeService {

    EventAttendee create(Event event, Character character, Iterable<CharacterRole> roles, LocalDateTime startDateTime, LocalDateTime finishDateTime, boolean recursive) {
        System.out.println("EVENT: " + event.getName());
        System.out.println("CHARACTER: " + character.getName());
        StringBuilder rolesStr = new StringBuilder();
        roles.forEach(role -> {
            rolesStr.append(" " + role + " ");
        });
        System.out.println("ROLES: " + rolesStr.toString());
        System.out.println("START: " + startDateTime.toString());
        System.out.println("FINISH: " + finishDateTime.toString());
        System.out.println("RECURSIVE: " + (recursive ? "true" : "false"));
        return new EventAttendee();
    }

}