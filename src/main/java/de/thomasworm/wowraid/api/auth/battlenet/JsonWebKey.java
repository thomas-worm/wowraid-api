package de.thomasworm.wowraid.api.auth.battlenet;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

class JsonWebKey implements Serializable {

    @JsonProperty("kty")
    private String keyType;

    @JsonProperty("alg")
    private String algorithm;

    @JsonProperty("use")
    private String publicKeyUse;

    @JsonProperty("kid")
    private String keyIdentifier;

    @JsonProperty("n")
    private String modulus;

    @JsonProperty("e")
    private String publicExponent;

    public void setKeyType(String value) {
        this.keyType = value;
    }

    public String getKeyType() {
        return this.keyType;
    }

    public void setAlgorithm(String value) {
        this.algorithm = value;
    }

    public String getAlgorithm() {
        return this.algorithm;
    }

    public void setPublicKeyUse(String value) {
        this.publicKeyUse = value;
    }

    public String getPublicKeyUse() {
        return this.publicKeyUse;
    }

    public void setKeyIdentifier(String value) {
        this.keyIdentifier = value;
    }

    public String getKeyIdentifier() {
        return this.keyIdentifier;
    }

    public void setModulus(String value) {
        this.modulus = value;
    }

    public String getModulus() {
        return this.modulus;
    }

    public void setPublicExponent(String value) {
        this.publicExponent = value;
    }

    public String getPublicExponent() {
        return this.publicExponent;
    }

}