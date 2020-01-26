package de.thomasworm.wowraid.api.model.persistence;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity()
public class EventAttendee {

    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "event_id")
    private Event event;

    @ManyToOne()
    @JoinColumn(name = "character_id")
    private Character character;

    @ManyToMany()
    @JoinTable(
        name = "event_attendee_roles",
        joinColumns = @JoinColumn(name = "attendee_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<CharacterRole> roles = new HashSet<>();

    public Long getId() {
        return this.id;
    }

    public void setEvent(Event value) {
        this.event = value;
    }

    public Event getEvent() {
        return this.event;
    }

    public void setCharacter(Character value) {
        this.character = value;
    }

    public Character getCharacter() {
        return this.character;
    }

    public Set<CharacterRole> getRoles() {
        return this.roles;
    }

}