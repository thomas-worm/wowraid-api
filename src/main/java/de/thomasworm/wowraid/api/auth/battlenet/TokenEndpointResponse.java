package de.thomasworm.wowraid.api.auth.battlenet;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

class TokenEndpointResponse implements Serializable {

    @JsonProperty("access_token")
    private String accessToken;

    @JsonProperty("token_type")
    private String tokenType;

    @JsonProperty("expires_in")
    private int expiresIn;

    @JsonProperty("scope")
    private String scope;

    @JsonProperty("id_token")
    private String idToken;

    public void setAccessToken(String value) {
        this.accessToken = value;
    }

    public String getAccessToken() {
        return this.accessToken;
    }

    public void setTokenType(String value) {
        this.tokenType = value;
    }

    public String getTokenType() {
        return this.tokenType;
    }

    public void setExpiresIn(int value) {
        this.expiresIn = value;
    }

    public int getExpiresIn() {
        return this.expiresIn;
    }

    public void setScope(String value) {
        this.scope = value;
    }

    public String getScope() {
        return this.scope;
    }

    public void setIdToken(String value) {
        this.idToken = value;
    }

    public String getIdToken() {
        return this.idToken;
    }

}