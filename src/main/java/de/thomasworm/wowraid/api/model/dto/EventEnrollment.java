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

}