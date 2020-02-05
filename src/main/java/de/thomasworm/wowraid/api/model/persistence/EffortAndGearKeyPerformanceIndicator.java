package de.thomasworm.wowraid.api.model.persistence;

/** import org.springframework.data.annotation.Immutable; **/

/** @Immutable() **/
public interface EffortAndGearKeyPerformanceIndicator {

    /** private **/ String battleTag = "";
    /** private **/ double effortPoints = 0;
    /** private **/ double gearPoints = 0;
    double priority = 0;

    /**public EffortAndGearKeyPerformanceIndicator(String battleTag, double effortPoints, double gearPoints) {
        this.battleTag = battleTag;
        this.effortPoints = effortPoints;
        this.gearPoints = gearPoints;
    }

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
    } **/

}