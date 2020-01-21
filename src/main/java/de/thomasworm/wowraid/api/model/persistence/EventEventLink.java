package de.thomasworm.wowraid.api.model.persistence;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity()
@Table(name = "event_event_link")
public class EventEventLink {

    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "start_event_id")
    private Event start;

    @ManyToOne()
    @JoinColumn(name = "target_event_id")
    private Event target;

    @Column()
    private String type;

    public Long getId() {
        return this.id;
    }

    public void setStart(Event value) {
        this.start = value;
    }

    public Event getStart() {
        return this.start;
    }

    public void setTarget(Event value) {
        this.target = value;
    }

    public Event getTarget() {
        return this.target;
    }

    public void setType(String value) {
        this.type = value;
    }

    public String getType() {
        return this.type;
    }

}