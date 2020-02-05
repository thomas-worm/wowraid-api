package de.thomasworm.wowraid.api.model.persistence;

import org.springframework.data.annotation.Immutable;

@Immutable()
public class EffortAndGearKeyPerformanceIndicator {

    private String battleTag;
    private double effortPoints;
    private double gearPoints;

    public String getBattleTag() {
        return this.battleTag;
    }

    public double getEffortPoints() {
        return this.effortPoints;
    }

    public double getGearPoints() {
        return this.gearPoints;
    }

    public double getPriority() {
        return (this.gearPoints == 0) ? this.effortPoints : this.effortPoints / this.gearPoints;
    }

}