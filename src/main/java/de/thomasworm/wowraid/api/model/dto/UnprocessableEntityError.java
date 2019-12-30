package de.thomasworm.wowraid.api.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UnprocessableEntityError {

    @JsonProperty("path")
    private String path; 
    
    @JsonProperty("error")
    private String error;

    @JsonProperty("errorMessage")
    private String errorMessage;

    public UnprocessableEntityError() {}

    public UnprocessableEntityError(String path, String error) {
        this();
        this.path = path;
        this.error = error;
    }

    public UnprocessableEntityError(String path, String error, String errorMessage) {
        this(path, error);
        this.errorMessage = errorMessage;
    }

    public void setPath(String value) {
        this.path = value;
    }

    public String getPath() {
        return this.path;
    }

    public void setError(String value) {
        this.error = value;
    }

    public String getError() {
        return this.error;
    }

    public void setErrorMessage(String value) {
        this.errorMessage = value;
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }

}