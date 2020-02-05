package de.thomasworm.wowraid.api.model.persistence;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OrderColumn;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity()
@Table(name = "account_transaction")
public class Transaction {

    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(
        fetch = FetchType.EAGER
    )
    @JoinColumn(name = "account_id")
    private Account account;

    @Column()
    @OrderColumn()
    private LocalDateTime dateTime;

    @ManyToOne()
    @JoinColumn(name = "currency_id")
    private Currency currency;

    @Column()
    private String title;

    @Column()
    @Lob()
    @Type(type = "org.hibernate.type.TextType")
    private String description;

    @Column()
    private double value;

    @ManyToMany(
        fetch = FetchType.EAGER
    )
    @JoinTable(
        name = "account_transaction_event_link",
        joinColumns = @JoinColumn(name = "transaction_id"),
        inverseJoinColumns = @JoinColumn(name = "event_id")
    )
    private Set<Event> linkedEvents = new HashSet<>();

    @ManyToMany(
        fetch = FetchType.EAGER
    )
    @JoinTable(
        name = "account_transaction_item_link",
        joinColumns = @JoinColumn(name = "transaction_id"),
        inverseJoinColumns = @JoinColumn(name = "item_id")
    )
    private Set<Item> linkedItems = new HashSet<>();

    @ManyToMany(
        fetch = FetchType.EAGER
    )
    @JoinTable(
        name = "account_transaction_character_link",
        joinColumns = @JoinColumn(name = "transaction_id"),
        inverseJoinColumns = @JoinColumn(name = "character_id")
    )
    private Set<Character> linkedCharacters = new HashSet<>();

    public Long getId() {
        return this.id;
    }

    public void setAccount(Account value) {
        this.account = value;
    }

    public Account getAccount() {
        return this.account;
    }

    public void setDateTime(LocalDateTime value) {
        this.dateTime = value;
    }

    public LocalDateTime getDateTime() {
        return this.dateTime;
    }

    public void setCurrency(Currency value) {
        this.currency = value;
    }

    public Currency getCurrency() {
        return this.currency;
    }

    public void setTitle(String value) {
        this.title = value;
    }

    public String getTitle() {
        return this.title;
    }

    public void setDescription(String value) {
        this.description = value;
    }

    public String getDescription() {
        return this.description;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public double getValue() {
        return this.value;
    }

    public Set<Event> getLinkedEvents() {
        return this.linkedEvents;
    }

    public Set<Item> getLinkedItems() {
        return this.linkedItems;
    }

    public Set<Character> getLinkedCharacters() {
        return this.linkedCharacters;
    }

}