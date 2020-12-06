package com.friedcoke.rmi;

import com.google.gson.Gson;

public class User {

    private final String username;
    private String password;
    private String status;

    public User(
            String username,
            String password,
            String status) {
        this.username = username;
        this.password = password;
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getStatus() {
        return status;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public static User fromJson(String userJson) {
        Gson gson = new Gson();
        return gson.fromJson(userJson, User.class);
    }
}
