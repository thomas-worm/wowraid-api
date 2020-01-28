package de.thomasworm.wowraid.api.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EventDrop {

    @JsonProperty("looter_realm")
    private String looterRealm;

    @JsonProperty("looter_name")
    private String looterName;

    @JsonProperty("looter_class")
    private String looterClass;

    @JsonProperty("looter_race")
    private String looterRace;

    @JsonProperty("looter_faction")
    private String looterFaction;

    @JsonProperty("item_blizzardIdentifier")
    private int itemBlizzardIdentifier;

    @JsonProperty("item_name")
    private String itemName;

    public void setLooterRealm(String value) {
        this.looterRealm = value;
    }

    public String getLooterRealm() {
        return this.looterRealm;
    }

    public void setLooterName(String value) {
        this.looterName = value;
    }

    public String getLooterName() {
        return this.looterName;
    }

    public void setLooterFaction(String value) {
        this.looterFaction = value;
    }

    public String getLooterFaction() {
        return this.looterFaction;
    }

    public void setLooterRace(String value) {
        this.looterRace = value;
    }

    public String getLooterRace() {
        return this.looterRace;
    }

    public void setLooterClass(String value) {
        this.looterClass = value;
    }

    public String getLooterClass() {
        return this.looterClass;
    }

    public void setItemBlizzardIdentifier(int value) {
        this.itemBlizzardIdentifier = value;
    }

    public int getItemBlizzardIdentifier() {
        return this.itemBlizzardIdentifier;
    }

    public void setItemName(String value) {
        this.itemName = value;
    }

    public String getItemName() {
        return this.itemName;
    }

}