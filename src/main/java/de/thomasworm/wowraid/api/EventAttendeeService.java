package de.thomasworm.wowraid.api;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.springframework.stereotype.Service;

import de.thomasworm.wowraid.api.model.persistence.Character;
import de.thomasworm.wowraid.api.model.persistence.CharacterRole;
import de.thomasworm.wowraid.api.model.persistence.Event;
import de.thomasworm.wowraid.api.model.persistence.EventAttendee;
import de.thomasworm.wowraid.api.model.persistence.EventAttendeeRepository;

@Service()
public class EventAttendeeService {

    private EventAttendeeRepository eventAttendeeRepository;

    public EventAttendeeService(EventAttendeeRepository eventAttendeeRepository) {
        this.eventAttendeeRepository = eventAttendeeRepository;
    }

    EventAttendee create(Event event, Character character, Iterable<CharacterRole> roles, LocalDateTime startDateTime, LocalDateTime finishDateTime, boolean recursive) {
        List<EventAttendee> attendees = new ArrayList<>();
        buildAttendee(attendees, event, character, roles, startDateTime, finishDateTime, recursive, new HashSet<>());
        this.eventAttendeeRepository.saveAll(attendees);
        return attendees.get(0);
    }

    private void buildAttendee(List<EventAttendee> attendeeStack, Event event, Character character, Iterable<CharacterRole> roles, LocalDateTime startDateTime, LocalDateTime finishDateTime, boolean recursive, HashSet<String> processedEvents) {
        EventAttendee attendee = new EventAttendee();
        attendee.setEvent(event);
        attendee.setCharacter(character);
        if (event.getStartDateTime().isBefore(startDateTime)) {
            if (event.getFinishDateTime().isAfter(startDateTime)) {
                attendee.setStartDateTime(startDateTime);
            } else {
                attendee.setStartDateTime(event.getFinishDateTime());
            }
        } else {
            attendee.setStartDateTime(event.getStartDateTime());
        }
        if (event.getFinishDateTime().isAfter(finishDateTime)) {
            if (event.getStartDateTime().isBefore(finishDateTime)) {
                attendee.setFinishDateTime(finishDateTime);
            } else {
                attendee.setFinishDateTime(event.getStartDateTime());
            }
        } else {
            attendee.setFinishDateTime(event.getFinishDateTime());
        }
        roles.forEach(role -> attendee.getRoles().add(role));
        if (recursive && !processedEvents.contains(event.getKey())) {
            processedEvents.add(event.getKey());
            List<Event> childEvents = new ArrayList<>();
            event.getEventLinks().forEach(eventLink -> {
                if (eventLink.getType().equals("child")) {
                    childEvents.add(eventLink.getTarget());
                }
            });
            event.getIncomingEventLinks().forEach(incomingEventLink -> {
                if (incomingEventLink.getType().equals("parent")) {
                    childEvents.add(incomingEventLink.getStart());
                }
            });
            childEvents.forEach(child -> {
                if (!attendee.getFinishDateTime().isBefore(child.getStartDateTime())
                 && !attendee.getStartDateTime().isAfter(child.getFinishDateTime())) {
                    buildAttendee(attendeeStack, child, character, roles, startDateTime, finishDateTime, recursive, (HashSet<String>)processedEvents.clone());
                }
            });
        }
        attendeeStack.add(attendee);
    }

}