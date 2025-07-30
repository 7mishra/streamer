package com.auth.payloads.response;

public class NewUserCreationResponse {
    private String username;
    private String message;
    private String token;

    // Constructors
    public NewUserCreationResponse() {
    }

    public NewUserCreationResponse(String username, String message, String token) {
        this.username = username;
        this.message = message;
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public String getMessage() {
        return message;
    }

    public String getToken() {
        return token;
    }

    // Setters

    public void setUsername(String username) {
        this.username = username;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
