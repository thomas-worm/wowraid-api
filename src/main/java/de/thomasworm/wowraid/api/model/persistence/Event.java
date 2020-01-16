package de.thomasworm.wowraid.api.model.persistence;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Entity()
public class Event {

    @StaticMetamodel(Event.class)
    public static class Columns {
        public static volatile SingularAttribute<Event, Long> id;
        public static volatile SingularAttribute<Event, String> key;
        public static volatile SingularAttribute<Event, String> name;
        public static volatile SingularAttribute<Event, String> description;
        public static volatile SingularAttribute<Event, LocalDateTime> startDateTime;
        public static volatile SingularAttribute<Event, LocalDateTime> finishDateTime;
        public static volatile SetAttribute<Event, Eventcategory> categories;
    }

    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column()
    private String name;

    @Column()
    private String description;

    @Column()
    private String key;

    @ManyToMany(
        mappedBy = "events"
    )
    private Set<Eventcategory> categories;

    @Column()
    private LocalDateTime startDateTime;

    @Column()
    private LocalDateTime finishDateTime;

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

}