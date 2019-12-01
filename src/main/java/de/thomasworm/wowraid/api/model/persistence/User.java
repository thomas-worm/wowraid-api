package de.thomasworm.wowraid.api.model.persistence;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity()
public class User {

    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column()
    private int blizzardIdentifier;

    @Column()
    private String battleTag;

    public Long getId() {
        return this.id;
    }

    public void setBlizzardIdentifier(int value) {
        this.blizzardIdentifier = value;
    }

    public int getBlizzardIdentifier() {
        return this.blizzardIdentifier;
    }

    public void setBattleTag(String value) {
        this.battleTag = value;
    }

    public String getBattleTag() {
        return this.battleTag;
    }

}
