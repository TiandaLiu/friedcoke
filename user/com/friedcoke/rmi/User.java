package com.friedcoke.rmi;

public class User {
    private String username;
    private String password;
    private String status;
    User(String userStr) {
        String[] strs = userStr.split(" ");
        username = strs[0];
        password = strs[1];
        status = strs[2];
    }
    User(String username, String password, String status) {
        this.username = username;
        this.password = password;
        this.status = status;
    }
    void setUsername(String username_) {
        username = username_;
    }
    void setPassword(String password_) {
        password = password_;
    }
    void setStatus(String status_) {
        status = status_;
    }
    String getUsername() {
        return username;
    }
    String getPassword() {
        return password;
    }
    String getStatus() {
        return status;
    }
    public String toString() {
        return username + " " + password + " " + status;
    }
}
