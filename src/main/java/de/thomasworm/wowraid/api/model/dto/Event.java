package de.thomasworm.wowraid.api.model.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

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

}