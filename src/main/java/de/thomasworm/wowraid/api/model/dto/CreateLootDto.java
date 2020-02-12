package de.thomasworm.wowraid.api.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateLootDto {

    @JsonProperty("item_blizzard_identifier")
    private Integer itemBlizzardIdentifier;

    @JsonProperty(
        value = "creature_blizzard_identifier",
        required = false
    )
    private Integer creatureBlizzardIdentifier;

    @JsonProperty(
        value = "event_key",
        required = false
    )
    private String eventKey;

    @JsonProperty(
        value = "character_realm",
        required = false
    )
    private String characterRealm;

    @JsonProperty(
        value = "character_name",
        required = false
    )
    private String characterName;

    public void setItemBlizzardIdentifier(Integer value) {
        this.itemBlizzardIdentifier = value;
    }

    public Integer getItemBlizzardIdentifier() {
        return this.itemBlizzardIdentifier;
    }

    public void setCreatureBlizzardIdentifier(Integer value) {
        this.creatureBlizzardIdentifier = value;
    }

    public Integer getCreatureBlizzardIdentifier() {
        return this.creatureBlizzardIdentifier;
    }

    public void setEventKey(String value) {
        this.eventKey = value;
    }

    public String getEventKey() {
        return this.eventKey;
    }

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

}