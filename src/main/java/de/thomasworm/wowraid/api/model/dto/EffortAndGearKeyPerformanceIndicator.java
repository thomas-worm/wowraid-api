package de.thomasworm.wowraid.api.model.dto;

import java.util.List;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EffortAndGearKeyPerformanceIndicator {

    @JsonProperty("battle_tag")
    private String battleTag;

    @JsonProperty("effort_points")
    private double effortPoints;

    @JsonProperty("effort_points_account")
    private String effortPointsAccount;

    @JsonProperty("gear_points")
    private double gearPoints;

    @JsonProperty("gear_points_account")
    private String gearPointsAccount;

    @JsonProperty("priority")
    private double priority;

    @JsonProperty(
        value = "characters",
        required = false
    )
    private List<LinkedCharacter> characters = new ArrayList<>();

    public void setBattleTag(String value) {
        this.battleTag = value;
    }

    public String getBattleTag() {
        return this.battleTag;
    }

    public void setEffortPoints(double value) {
        this.effortPoints = value;
    }

    public double getEffortPoints() {
        return this.effortPoints;
    }

    public void setEffortPointsAccount(String value) {
        this.effortPointsAccount = value;
    }

    public String getEffortPointsAccount() {
        return this.effortPointsAccount;
    }

    public void setGearPoints(double value) {
        this.gearPoints = value;
    }

    public double getGearPoints() {
        return this.gearPoints;
    }

    public void setGearPointsAccount(String value) {
        this.gearPointsAccount = value;
    }

    public String getGearPointsAccount() {
        return this.gearPointsAccount;
    }

    public void setPriority(double value) {
        this.priority = value;
    }

    public double getPriority() {
        return this.priority;
    }

    public List<LinkedCharacter> getCharacters() {
        return this.characters;
    }

}