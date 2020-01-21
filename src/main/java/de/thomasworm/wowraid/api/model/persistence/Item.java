package de.thomasworm.wowraid.api.model.persistence;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity()
public class Item {

    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column()
    private int blizzardIdentifier;

    @Column()
    private String name;

    @ManyToMany(
        mappedBy = "drops"
    )
    private Set<Event> events;

    public Long getId() {
        return this.id;
    }

    public void setBlizzardIdentifier(int value) {
        this.blizzardIdentifier = value;
    }

    public int getBlizzardIdentifier() {
        return this.blizzardIdentifier;
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