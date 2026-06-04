package ru.itis.hw48.handler;

import java.time.LocalDateTime;
import java.util.Map;

public class ValidationErrorResponse {

    private String message;
    private LocalDateTime timestamp;
    private Map<String, String> errors;

    public ValidationErrorResponse(String message, LocalDateTime timestamp, Map<String, String> errors) {
        this.message = message;
        this.timestamp = timestamp;
        this.errors = errors;
    }

    public ValidationErrorResponse() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public void setErrors(Map<String, String> errors) {
        this.errors = errors;
    }
}
