package de.thomasworm.wowraid.api.model.persistence;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity()
@Table(name = "user_epgp_accounts")
public class EffortAndGearAccountMapping {

    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "effort_account_id")
    private Account effortAccount;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "gear_account_id")
    private Account gearAccount;

    public void setUser(User value) {
        this.user = value;
    }

    public User getUser() {
        return this.user;
    }

    public void setEffortAccount(Account value) {
        this.effortAccount = value;
    }

    public Account getEffortAccount() {
        return this.effortAccount;
    }

    public void setGearAccount(Account value) {
        this.gearAccount = value;
    }

    public Account getGearAccount() {
        return this.gearAccount;
    }

}