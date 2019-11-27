package de.thomasworm.wowraid.api.auth.battlenet;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

class JsonWebTokenHeader implements Serializable {

    @JsonProperty("kid")
    private String keyIdentifier;

    @JsonProperty("typ")
    private String type;

    @JsonProperty("alg")
    private String algorithm;

    public void setKeyIdentifier(String value) {
        this.keyIdentifier = value;
    }

    public String getKeyIdentifier() {
        return this.keyIdentifier;
    }

    public void setType(String value) {
        this.type = value;
    }

    public String getType() {
        return this.type;
    }

    public void setAlgorithm(String value) {
        this.algorithm = value;
    }

    public String getAlgorithm() {
        return this.algorithm;
    }

}