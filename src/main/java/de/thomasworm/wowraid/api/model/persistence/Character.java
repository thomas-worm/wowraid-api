package de.thomasworm.wowraid.api.model.persistence;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity()
public class Character {

    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = true)
    @JoinColumn()
    private User user;

    @Column()
    private String name;

    @OneToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn()
    private Realm realm;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "class_id")
    private CharacterClass characterClass;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn()
    private Race race;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn()
    private Faction faction;

    public Long getId() {
        return this.id;
    }

    public void setUser(User value) {
        this.user = value;
    }

    public User getUser() {
        return this.user;
    }

    public void setName(String value) {
        this.name = value;
    }

    public String getName() {
        return this.name;
    }

    public void setCharacterClass(CharacterClass value) {
        this.characterClass = value;
    }

    public CharacterClass getCharacterClass() {
        return this.characterClass;
    }

    public void setRace(Race value) {
        this.race = value;
    }

    public Race getRace() {
        return this.race;
    }

    public void setFaction(Faction value) {
        this.faction = value;
    }

    public Faction getFaction() {
        return this.faction;
    }

    public void setRealm(Realm value) {
        this.realm = value;
    }

    public Realm getRealm() {
        return this.realm;
    }

}