package de.thomasworm.wowraid.api.model.persistence;

import org.springframework.data.annotation.Immutable;

@Immutable()
public class EffortAndGearKeyPerformanceIndicator {

    private User user;
    private double effortPoints;
    private double gearPoints;
    private double priority;

    public EffortAndGearKeyPerformanceIndicator(User user, double effortPoints, double gearPoints, double priority) {
        this.user = user;
        this.effortPoints = effortPoints;
        this.gearPoints = gearPoints;
        this.priority = priority;
    }

    public User getUser() {
        return this.user;
    }

    public double getEffortPoints() {
        return this.effortPoints;
    }

    public double getGearPoints() {
        return this.gearPoints;
    }

    public double getPriority() {
        //return (this.gearPoints == 0) ? this.effortPoints : this.effortPoints / this.gearPoints;
        return this.priority;
    }

}