package de.thomasworm.wowraid.api.model.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Transaction {

    @JsonProperty("date_time")
    private LocalDateTime dateTime;

    @JsonProperty("title")
    private String title;

    @JsonProperty("value")
    private double value;

    @JsonProperty("currency")
    private String currency;

    public void setDateTime(LocalDateTime value) {
        this.dateTime = value;
    }

    public LocalDateTime getDateTime() {
        return this.dateTime;
    }

    public void setTitle(String value) {
        this.title = value;
    }

    public String getTitle() {
        return this.title;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public double getValue() {
        return this.value;
    }

    public void setCurrency(String value) {
        this.currency = value;
    }

    public String getCurrency() {
        return this.currency;
    }

}