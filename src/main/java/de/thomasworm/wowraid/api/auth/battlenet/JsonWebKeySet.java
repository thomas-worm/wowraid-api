package de.thomasworm.wowraid.api.auth.battlenet;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

class JsonWebKeySet implements Serializable {

    @JsonProperty("keys")
    private List<JsonWebKey> keys = new ArrayList<>();

    public void setKeys(List<JsonWebKey> value) {
        this.keys = value;
    }

    public List<JsonWebKey> getKeys() {
        return this.keys;
    }

}