package de.thomasworm.wowraid.api.auth.battlenet;

class JsonWebToken {

    private JsonWebTokenHeader header;

    private JsonWebTokenBody body;

    private boolean valid = false;

    public void setHeader(JsonWebTokenHeader value) {
        this.header = value;
    }

    public JsonWebTokenHeader getHeader() {
        return this.header;
    }

    public void setBody(JsonWebTokenBody value) {
        this.body = value;
    }

    public JsonWebTokenBody getBody() {
        return this.body;
    }

    public void setValid(boolean value) {
        this.valid = value;
    }

    public boolean isValid() {
        return this.valid;
    }

}