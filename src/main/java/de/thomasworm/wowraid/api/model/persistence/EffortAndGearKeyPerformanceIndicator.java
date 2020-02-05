package de.thomasworm.wowraid.api.model.persistence;

import org.springframework.data.annotation.Immutable;

@Immutable()
public class EffortAndGearKeyPerformanceIndicator {

    private User user;
    private double effortPoints;
    private Account effortPointsAccount;
    private double gearPoints;
    private Account gearPointsAccount;
    private double priority;

    public EffortAndGearKeyPerformanceIndicator(User user, double effortPoints, Account effortPointsAccount, double gearPoints, Account gearPointsAccount, double priority) {
        this.user = user;
        this.effortPoints = effortPoints;
        this.effortPointsAccount = effortPointsAccount;
        this.gearPoints = gearPoints;
        this.gearPointsAccount = gearPointsAccount;
        this.priority = priority;
    }

    public User getUser() {
        return this.user;
    }

    public double getEffortPoints() {
        return this.effortPoints;
    }

    public Account getEffortPointsAccount() {
        return this.effortPointsAccount;
    }
 
    public double getGearPoints() {
        return this.gearPoints;
    }

    public Account getGearPointsAccount() {
        return this.gearPointsAccount;
    }

    public double getPriority() {
        return this.priority;
    }

}