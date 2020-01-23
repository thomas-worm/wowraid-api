package de.thomasworm.wowraid.api.model.persistence;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity()
public class Loot {

    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "event_id")
    private Event droppedIn;

    @ManyToOne()
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne()
    @JoinColumn(name = "character_id")
    private Character looter;

    @OneToOne(
        optional = true
    )
    @JoinColumn(name = "creature_id")
    private Creature droppedBy;

    public Long getId() {
        return this.id;
    }

    public void setDroppedIn(Event value) {
        this.droppedIn = value;
    }

    public Event getDroppedIn() {
        return this.droppedIn;
    }

    public void setItem(Item value) {
        this.item = value;
    }

    public Item getItem() {
        return this.item;
    }

    public void setLooter(Character value) {
        this.looter = value;
    }

    public Character getLooter() {
        return this.looter;
    }

    public void setDroppedBy(Creature value) {
        this.droppedBy = value;
    }

    public Creature getDroppedBy() {
        return this.droppedBy;
    }

}