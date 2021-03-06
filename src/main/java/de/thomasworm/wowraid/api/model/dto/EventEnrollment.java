package de.thomasworm.wowraid.api.model.dto;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EventEnrollment {

    @JsonProperty("character_realm")
    private String characterRealm;

    @JsonProperty("character_name")
    private String characterName;

    @JsonProperty(
        value = "character_class",
        required = false
    )
    private String characterClass;

    @JsonProperty(
        value = "character_race",
        required = false
    )
    private String characterRace;

    @JsonProperty(
        value = "character_faction",
        required = false
    )
    private String characterFaction;

    @JsonProperty("roles")
    private Set<String> roles = new HashSet<>();

    @JsonProperty("state")
    private String state;

    public void setCharacterRealm(String value) {
        this.characterRealm = value;
    }

    public String getCharacterRealm() {
        return this.characterRealm;
    }

    public void setCharacterName(String value) {
        this.characterName = value;
    }

    public String getCharacterName() {
        return this.characterName;
    }

    public void setCharacterFaction(String value) {
        this.characterFaction = value;
    }

    public String getCharacterFaction() {
        return this.characterFaction;
    }

    public void setCharacterRace(String value) {
        this.characterRace = value;
    }

    public String getCharacterRace() {
        return this.characterRace;
    }

    public void setCharacterClass(String value) {
        this.characterClass = value;
    }

    public String getCharacterClass() {
        return this.characterClass;
    }

    public Set<String> getRoles() {
        return this.roles;
    }

    public void setState(String value) {
        this.state = value;
    }

    public String getState() {
        return this.state;
    }

}