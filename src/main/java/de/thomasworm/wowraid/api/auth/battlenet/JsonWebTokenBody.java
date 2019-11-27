package de.thomasworm.wowraid.api.auth.battlenet;

import java.io.Serializable;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonProperty;

class JsonWebTokenBody implements Serializable {

    @JsonProperty("at_hash")
    private String hashValue;

    @JsonProperty("sub")
    private String subject;

    @JsonProperty("aud")
    private String audience;

    @JsonProperty("azp")
    private String authorizedParty;

    @JsonProperty("iss")
    private String issuer;

    @JsonProperty("exp")
    private int expirationTime;

    @JsonProperty("iat")
    private int issuedAtTime;

    @JsonProperty("battle_tag")
    private Optional<String> battleTag;

    @JsonProperty("jti")
    private String identifier;

    public void setHashValue(String value) {
        this.hashValue = value;
    }

    public String getHashValue() {
        return this.hashValue;
    }

    public void setSubject(String value) {
        this.subject = value;
    }

    public String getSubject() {
        return this.subject;
    }

    public void setAudience(String value) {
        this.audience = value;
    }

    public String getAudience() {
        return this.audience;
    }

    public void setAuthorizedParty(String value) {
        this.authorizedParty = value;
    }

    public String getAuthorizedParty() {
        return this.authorizedParty;
    }

    public void setIssuer(String value) {
        this.issuer = value;
    }

    public String getIssuer() {
        return this.issuer;
    }

    public void setExpirationTime(int value) {
        this.expirationTime = value;
    }

    public int getExpirationTime() {
        return this.expirationTime;
    }

    public void setIssuedAtTime(int value) {
        this.issuedAtTime = value;
    }

    public int getIssuedAtTime() {
        return this.issuedAtTime;
    }

    public void setBattleTag(Optional<String> value) {
        this.battleTag = value;
    }

    public Optional<String> getBattleTag() {
        return this.battleTag;
    }

    public void setIdentifier(String value) {
        this.identifier = value;
    }

    public String getIdentifier() {
        return this.identifier;
    }

}