package de.thomasworm.wowraid.api.model.persistence;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity()
public class Account {

    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column()
    private String key;

    @Column()
    private String name;

    @ManyToOne(
        fetch = FetchType.EAGER
    )
    @JoinColumn(name = "currency_id")
    private Currency currency;

    @ManyToOne(
        fetch = FetchType.LAZY
    )
    @JoinColumn(name = "user_id")
    private User owner;

    @OneToMany(
        mappedBy = "account",
        fetch = FetchType.EAGER
    )
    private Set<Transaction> transactions = new HashSet<>();

    public Long getId() {
        return this.id;
    }

    public void setKey(String value) {
        this.key = value;
    }

    public String getKey() {
        return this.key;
    }

    public void setName(String value) {
        this.name = value;
    }

    public void setName(String value, boolean generateKey) {
        this.name = value;
        this.key = value.toLowerCase().replaceAll("\\s","_").replaceAll("[^a-z0-9_]", "-");
    }

    public String getName() {
        return this.name;
    }

    public void setCurrency(Currency value) {
        this.currency = value;
    }

    public Currency getCurrency() {
        return this.currency;
    }

    public void setOwner(User value) {
        this.owner = value;
    }

    public User getOwner() {
        return this.owner;
    }

    public Set<Transaction> getTransactions() {
        return this.transactions;
    }

}