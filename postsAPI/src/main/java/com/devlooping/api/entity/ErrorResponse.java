package com.devlooping.api.entity;

import java.time.LocalDateTime;

public class ErrorResponse {
    private int httpStatus;
    private String statusDescription;
    private String message;
    private LocalDateTime timestamp;

    public ErrorResponse(int httpStatus, String statusDescription, String message) {
        this.httpStatus = httpStatus;
        this.statusDescription = statusDescription;
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }

    public int getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(int httpStatus) {
        this.httpStatus = httpStatus;
    }

    public String getStatusDescription() {
        return statusDescription;
    }

    public void setStatusDescription(String statusDescription) {
        this.statusDescription = statusDescription;
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


}
