package de.thomasworm.wowraid.api.model.dto;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserInfo {

    @JsonProperty("battleTag")
    private String battleTag;

    @JsonProperty("userIdentifier")
    private int userIdentifier;

    @JsonProperty("authenticated")
    private boolean authenticated = false;

    @JsonProperty("groups")
    private Set<String> groups = new HashSet<>();

    public void setBattleTag(String value) {
        this.battleTag = value;
    }

    public String getBattleTag() {
        return this.battleTag;
    }

    public void setUserIdentifier(int value) {
        this.userIdentifier = value;
    }

    public int getUserIdentifier() {
        return this.userIdentifier;
    }

    public void setAuthenticated(boolean value) {
        this.authenticated = value;
    }

    public boolean isAuthenticated() {
        return this.authenticated;
    }

    public Set<String> getGroups() {
        return this.groups;
    }

}