package de.thomasworm.wowraid.api.model.persistence;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity()
public class EventEnrollment {

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
        name = "event_enrollment_roles",
        joinColumns = @JoinColumn(name = "enrollment_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<CharacterRole> roles = new HashSet<>();

    @Column()
    private LocalDateTime startDateTime;

    @Column()
    private LocalDateTime finishDateTime;

    @OneToOne()
    @JoinColumn(name = "state_id")
    private EventEnrollmentState state;

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

    public void setState(EventEnrollmentState value) {
        this.state = value;
    }

    public EventEnrollmentState getState() {
        return this.state;
    }

    public void setStartDateTime(LocalDateTime value) {
        this.startDateTime = value;
    }

    public LocalDateTime getStartDateTime() {
        return this.startDateTime;
    }

    public void setFinishDateTime(LocalDateTime value) {
        this.finishDateTime = value;
    }

    public LocalDateTime getFinishDateTime() {
        return this.finishDateTime;
    }

}