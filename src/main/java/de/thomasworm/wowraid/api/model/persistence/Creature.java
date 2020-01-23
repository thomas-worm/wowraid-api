package de.thomasworm.wowraid.api.model.persistence;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.Type;

@Entity()
public class Creature {

    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column()
    private String name;

    @Column()
    private int blizzardIdentifier;

    @Column()
    private String type;

    @Column()
    @Lob()
    @Type(type = "org.hibernate.type.TextType")
    private String description;

    @ManyToMany(
        mappedBy = "creatures"
    )
    private Set<Event> events = new HashSet<>();

    public Long getId() {
        return this.id;
    }

    public void setName(String value) {
        this.name = value;
    }

    public String getName() {
        return this.name;
    }

    public void setBlizzardIdentifier(int value) {
        this.blizzardIdentifier = value;
    }

    public int getBlizzardIdentifier() {
        return this.blizzardIdentifier;
    }

    public void setType(String value) {
        this.type = value;
    }

    public String getType() {
        return this.type;
    }

    public void setDescription(String value) {
        this.description = value;
    }

    public String getDescription() {
        return this.description;
    }

    public Set<Event> getEvents() {
        return this.events;
    }

}