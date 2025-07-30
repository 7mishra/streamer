package com.auth.payloads.request;

public class UserLogin {
    private String username;
    private String password;

    // Constructors
    public UserLogin() {
    }

    public UserLogin(String username, String password, String salt) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    // Setters
    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}

