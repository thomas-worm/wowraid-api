package de.thomasworm.wowraid.api.model.dto;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EventAttendee {

    @JsonProperty("character_realm")
    private String characterRealm;

    @JsonProperty("character_name")
    private String characterName;

    @JsonProperty("character_class")
    private String characterClass;

    @JsonProperty("character_race")
    private String characterRace;

    @JsonProperty("character_faction")
    private String characterFaction;

    @JsonProperty("roles")
    private Set<String> roles = new HashSet<>();

    @JsonProperty("start_datetime")
    LocalDateTime startDateTime;

    @JsonProperty("finish_datetime")
    LocalDateTime finishDateTime;

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