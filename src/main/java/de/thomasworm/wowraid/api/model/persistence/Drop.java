package de.thomasworm.wowraid.api.model.persistence;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity()
public class Drop {

    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(
        fetch = FetchType.EAGER
    )
    @JoinColumn(name = "creature_id")
    private Creature creature;

    @ManyToOne(
        fetch = FetchType.EAGER
    )
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne(
        fetch = FetchType.EAGER,
        optional = true
    )
    @JoinColumn(name = "looter_character_id")
    private Character looter;

    public Long getId() {
        return this.id;
    }

    public void setCreature(Creature value) {
        this.creature = value;
    }

    public Creature getCreature() {
        return this.creature;
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

}