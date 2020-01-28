package de.thomasworm.wowraid.api.model.persistence;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Type;

@Entity()
public class Event {

    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column()
    private String name;

    @Column()
    @Lob()
    @Type(type = "org.hibernate.type.TextType")
    private String description;

    @Column()
    private String key;

    @ManyToMany(
        mappedBy = "events",
        fetch = FetchType.EAGER
    )
    private Set<Eventcategory> categories = new HashSet<>();

    @Column()
    private LocalDateTime startDateTime;

    @Column()
    private LocalDateTime finishDateTime;

    @OneToMany(
        mappedBy = "start",
        fetch = FetchType.EAGER
    )
    private List<EventEventLink> eventLinks = new ArrayList<>();

    @OneToMany(
        mappedBy = "target",
        fetch = FetchType.EAGER
    )
    private List<EventEventLink> incomingEventLinks;

    @ManyToMany(
        fetch = FetchType.EAGER
    )
    @JoinTable(
        name = "event_drops",
        joinColumns = @JoinColumn(name = "event_id"),
        inverseJoinColumns = @JoinColumn(name = "drop_id")
    )
    private Set<Drop> drops;

    @ManyToMany(
        fetch = FetchType.EAGER
    )
    @JoinTable(
        name = "event_creatures",
        joinColumns = @JoinColumn(name = "event_id"),
        inverseJoinColumns = @JoinColumn(name = "creature_id")
    )
    private Set<Creature> creatures = new HashSet<>();

    @Column()
    private boolean enrollPossible;

    @OneToMany(
        mappedBy = "event",
        fetch = FetchType.EAGER
    )
    private Set<EventEnrollment> enrollments;

    @OneToMany(
        mappedBy = "event",
        fetch = FetchType.EAGER
    )
    private Set<EventAttendee> attendees;

    public Long getId() {
        return this.id;
    }

    public void setKey(String value) {
        this.key = value;
    }

    public String getKey() {
        return this.key;
    }

    public void setName(String value) {
        this.setName(value, false);
    }

    public void setName(String value, boolean generateKey) {
        this.name = value;
        this.key = value.toLowerCase().replaceAll("\\s","_").replaceAll("[^a-z0-9_]", "-");
    }

    public String getName() {
        return this.name;
    }

    public void setDescription(String value) {
        this.description = value;
    }

    public String getDescription() {
        return this.description;
    }

    public Set<Eventcategory> getCategories() {
        return this.categories;
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

    public List<EventEventLink> getEventLinks() {
        return this.eventLinks;
    }

    public List<EventEventLink> getIncomingEventLinks() {
        return this.incomingEventLinks;
    }

    public Set<Drop> getDrops() {
        return this.drops;
    }

    public Set<Creature> getCreatures() {
        return this.creatures;
    }

    public void setEnrollPossible(boolean value) {
        this.enrollPossible = value;
    }

    public boolean isEnrollPossible() {
        return this.enrollPossible;
    }

    public Set<EventEnrollment> getEnrollments() {
        return this.enrollments;
    }

    public Set<EventAttendee> getAttendees() {
        return this.attendees;
    }

}