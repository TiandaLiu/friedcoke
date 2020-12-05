package com.friedcoke.webserver.model;

import com.google.gson.Gson;

import java.util.UUID;

public class Notification {

    private UUID sender;
    private UUID receiver;
    private String subject;
    private String content;
    private String timestamp;

    public Notification(UUID sender, UUID receiver, String subject, String content, String timestamp) {
        this.sender = sender;
        this.receiver = receiver;
        this.subject = subject;
        this.content = content;
        this.timestamp = timestamp;
    }

    public UUID getSender() {
        return sender;
    }

    public UUID getReceiver() {
        return receiver;
    }

    public String getSubject() {
        return subject;
    }

    public String getContent() {
        return content;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setSender(UUID sender) {
        this.sender = sender;
    }

    public void setReceiver(UUID receiver) {
        this.receiver = receiver;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public static Notification fromJson(String notificationJson) {
        Gson gson = new Gson();
        return gson.fromJson(notificationJson, Notification.class);
    }
}
