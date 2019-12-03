package de.thomasworm.wowraid.api.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Character {

    @JsonProperty("realm")
    private String realm;

    @JsonProperty("name")
    private String name;

    @JsonProperty("class")
    private String characterClass;

    @JsonProperty("race")
    private String race;

    @JsonProperty("faction")
    private String faction;

    public void setRealm(String value) {
        this.realm = value;
    }

    public String getRealm() {
        return this.realm;
    }

    public void setName(String value) {
        this.name = value;
    }

    public String getName() {
        return this.name;
    }

    public void setCharacterClass(String value) {
        this.characterClass = value;
    }

    public String getCharacterClass() {
        return this.characterClass;
    }

    public void setRace(String value) {
        this.race = value;
    }

    public String getRace() {
        return this.race;
    }

    public void setFaction(String value) {
        this.faction = value;
    }

    public String getFaction() {
        return this.faction;
    }
}