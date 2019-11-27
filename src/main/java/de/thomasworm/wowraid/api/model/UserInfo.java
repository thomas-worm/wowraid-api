package de.thomasworm.wowraid.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;

public class UserInfo {

    @JsonProperty("battleTag")
    private String battleTag;

    @JsonProperty("userIdentifier")
    private int userIdentifier;

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

    public UserInfo() {}

    public UserInfo(OAuth2AuthenticationToken oAuth2AuthenticationToken) {
        this.setBattleTag(oAuth2AuthenticationToken.getPrincipal().getAttribute("battle_tag"));
        this.setUserIdentifier(Integer.parseInt(oAuth2AuthenticationToken.getPrincipal().getName()));
    }

}