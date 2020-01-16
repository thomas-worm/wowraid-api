package de.thomasworm.wowraid.api.model.persistence;

public class DuplicateKeyException extends Exception {

    private static final long serialVersionUID = 6487642956357464875L;

    private Object existingRecord;

    public void setExisitingRecord(Object value) {
        this.existingRecord = value;
    }

    public Object getExistingRecord() {
        return this.existingRecord;
    }

    public <T> T getExistingRecord(Class<T> type) {
        return (T) this.existingRecord;
    }

    public static DuplicateKeyException withExistingRecord(Object existingRecord) {
        DuplicateKeyException exception = new DuplicateKeyException();
        exception.setExisitingRecord(existingRecord);
        return exception;
    }

}