package de.thomasworm.wowraid.api.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Creature {

    @JsonProperty("name")
    private String name;

    @JsonProperty("blizzard_identifier")
    private int blizzardIdentifier;

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

}