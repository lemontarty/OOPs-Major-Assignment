package com.example.demo.api;

public class ErrorResponse {
    private String error;

    public ErrorResponse(String error) {
        this.error = error;
    }

    // Getter
    public String getError() {
        return error;
    }
}
