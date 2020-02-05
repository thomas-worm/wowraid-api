package de.thomasworm.wowraid.api.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EffortAndGearKeyPerformanceIndicator {

    @JsonProperty("battle_tag")
    private String battleTag;

    @JsonProperty("effort_points")
    private double effortPoints;

    @JsonProperty("gear_points")
    private double gearPoints;

    @JsonProperty("priority")
    private double priority;

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

    public void setGearPoints(double value) {
        this.gearPoints = value;
    }

    public double getGearPoints() {
        return this.gearPoints;
    }

    public void setPriority(double value) {
        this.priority = value;
    }

    public double getPriority() {
        return this.priority;
    }

}