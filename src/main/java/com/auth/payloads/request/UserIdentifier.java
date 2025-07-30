package com.auth.payloads.request;

public class UserIdentifier {
    private String token;

    public UserIdentifier(String token) {
        this.token = token;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
