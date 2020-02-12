package de.thomasworm.wowraid.api.model.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Account {

    @JsonProperty("name")
    private String name;

    @JsonProperty("transactions")
    private List<Transaction> transactions = new ArrayList<>();

    public void setName(String value) {
        this.name = value;
    }

    public String getName() {
        return this.name;
    }

    public List<Transaction> getTransactions() {
        return this.transactions;
    }

}