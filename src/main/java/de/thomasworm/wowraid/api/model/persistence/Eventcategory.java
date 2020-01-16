package de.thomasworm.wowraid.api.model.persistence;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Entity()
public class Eventcategory {

    @StaticMetamodel(Eventcategory.class)
    public static class Columns {
        public static volatile SingularAttribute<Eventcategory, Long> id;
        public static volatile SingularAttribute<Eventcategory, String> name;
        public static volatile SetAttribute<Eventcategory, Event> events;
    }

    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column()
    private String name;

    @ManyToMany()
    @JoinTable(
        name = "eventcategory_events",
        joinColumns = @JoinColumn(name = "eventcategory_id"),
        inverseJoinColumns = @JoinColumn(name = "event_id")
    )
    private Set<Event> events;

    public Long getId() {
        return this.id;
    }

    public void setName(String value) {
        this.name = value;
    }

    public String getName() {
        return this.name;
    }

    public Set<Event> getEvents() {
        return this.events;
    }

}