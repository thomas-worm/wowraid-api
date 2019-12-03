package de.thomasworm.wowraid.api.model.persistence;

import java.util.Optional;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity()
public class Character {

    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne()
    @JoinColumn()
    private Optional<User> user;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "class")
    private CharacterClass characterClass;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn()
    private CharacterRace race;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn()
    private Faction faction;

    public Long getId() {
        return this.id;
    }

    public void setUser(Optional<User> value) {
        this.user = value;
    }

    public Optional<User> getUser() {
        return this.user;
    }

    public void setCharacterClass(CharacterClass value) {
        this.characterClass = value;
    }

    public CharacterClass getCharacterClass() {
        return this.characterClass;
    }

    public void setRace(CharacterRace value) {
        this.race = value;
    }

    public CharacterRace getRace() {
        return this.race;
    }

    public void setFaction(Faction faction) {
        this.faction = faction;
    }

    public Faction getFaction() {
        return this.faction;
    }

}