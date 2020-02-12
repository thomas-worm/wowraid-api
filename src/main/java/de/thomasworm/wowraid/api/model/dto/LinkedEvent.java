package de.thomasworm.wowraid.api.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LinkedEvent {

    @JsonProperty("key")
    private String key;

    @JsonProperty("name")
    private String name;

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

}