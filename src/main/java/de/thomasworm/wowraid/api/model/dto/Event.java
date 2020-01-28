package de.thomasworm.wowraid.api.model.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import de.thomasworm.wowraid.api.model.persistence.EventEnrollment;

public class Event {

    @JsonProperty("key")
    private String key;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("categories")
    private List<String> categories = new ArrayList<>();

    @JsonProperty("start_datetime")
    LocalDateTime startDateTime;

    @JsonProperty("finish_datetime")
    LocalDateTime finishDateTime;

    @JsonProperty("childs")
    private List<Event> childs = new ArrayList<>();

    @JsonProperty("enrollments")
    private List<EventEnrollment> enrollments = new ArrayList<>();

    @JsonProperty("attendees")
    private List<EventAttendee> attendees = new ArrayList<>();

    @JsonProperty("drops")
    private List<EventDrop> drops = new ArrayList<>();

    public void setKey(String value) {
        this.key = value;
    }

    public String getKey() {
        return this.key;
    }

    public void setName(String value) {
        this.name = value;
    }

    public String getName() {
        return this.name;
    }

    public void setDescription(String value) {
        this.description = value;
    }

    public String getDescription() {
        return this.description;
    }

    public List<String> getCategories() {
        return this.categories;
    }

    public void setStartDateTime(LocalDateTime value) {
        this.startDateTime = value;
    }

    public LocalDateTime getStartDateTime() {
        return this.startDateTime;
    }

    public void setFinishDateTime(LocalDateTime value) {
        this.finishDateTime = value;
    }

    public LocalDateTime getFinishDateTime() {
        return this.finishDateTime;
    }

    public List<Event> getChilds() {
        return this.childs;
    }

    public List<EventEnrollment> getEnrollments() {
        return this.enrollments;
    }

    public List<EventAttendee> getAttendees() {
        return this.attendees;
    }

    public List<EventDrop> getDrops() {
        return this.drops;
    }

}