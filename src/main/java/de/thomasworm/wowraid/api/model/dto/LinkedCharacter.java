package de.thomasworm.wowraid.api.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LinkedCharacter {

    @JsonProperty("realm")
    private String realm;

    @JsonProperty("name")
    private String name;

    public void setRealm(String value) {
        this.realm = value;
    }

    public String getRealm() {
        return this.realm;
    }

    public void setName(String value) {
        this.name = value;
    }

    public String getName() {
        return this.name;
    }

}