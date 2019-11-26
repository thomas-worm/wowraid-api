package de.thomasworm.wowraid.api.auth.battlenet;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

class OpenIdConfiguration implements Serializable {

    @JsonProperty("issuer")
    private String issuer;

    @JsonProperty("authorization_endpoint")
    private String authorizationEndpoint;

    @JsonProperty("token_endpoint")
    private String tokenEndpoint;

    @JsonProperty("jwks_uri")
    private String jwksUri;

    @JsonProperty("response_types_supported")
    private String[] responseTypesSupported;

    @JsonProperty("userinfo_endpoint")
    private String userinfoEndpoint;

    @JsonProperty("subject_types_supported")
    private String[] subjectTypesSupported;

    @JsonProperty("id_token_signing_alg_values_supported")
    private String[] idTokenSigningAlgValuesSupported;

    @JsonProperty("grant_types_supported")
    private String[] grantTypesSupported;

    public void setIssuer(String value) {
        this.issuer = value;
    }

    public String getIssuer() {
        return this.issuer;
    }

    public void setAuthorizationEndpoint(String value) {
        this.authorizationEndpoint = value;
    }

    public String getAuthorizationEndpoint() {
        return this.authorizationEndpoint;
    }

    public void setTokenEndpoint(String value) {
        this.tokenEndpoint = value;
    }

    public String getTokenEndpoint() {
        return this.tokenEndpoint;
    }

    public void setJwksUri(String value) {
        this.jwksUri = value;
    }

    public String getJwksUri() {
        return this.jwksUri;
    }

    public void setResponseTypesSupported(String[] value) {
        this.responseTypesSupported = value;
    }

    public String[] getResponseTypesSupported() {
        return this.responseTypesSupported;
    }

    public void setUserinfoEndpoint(String value) {
        this.userinfoEndpoint = value;
    }

    public String getUserinfoEndpoint() {
        return this.userinfoEndpoint;
    }

    public void setSubjectTypesSupported(String[] value) {
        this.subjectTypesSupported = value;
    }

    public String[] getSubjectTypesSupported() {
        return this.subjectTypesSupported;
    }

    public void setIdTokenSigningAlgValuesSupported(String[] value) {
        this.idTokenSigningAlgValuesSupported = value;
    }

    public String[] getIdTokenSigningAlgValuesSupported() {
        return this.idTokenSigningAlgValuesSupported;
    }

    public void setGrantTypesSupported(String[] value) {
        this.grantTypesSupported = value;
    }

    public String[] getGrantTypesSupported() {
        return this.grantTypesSupported;
    }

}