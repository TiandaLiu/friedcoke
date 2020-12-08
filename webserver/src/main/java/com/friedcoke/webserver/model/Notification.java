package com.friedcoke.webserver.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.Gson;

public class Notification {

    private String sender;
    private String receiver;
    private String subject;
    private String content;
    private String timestamp;

    public Notification(
            @JsonProperty("sender") String sender,
            @JsonProperty("receiver") String receiver,
            @JsonProperty("subject") String subject,
            @JsonProperty("content") String content,
            @JsonProperty("timestamp") String timestamp) {
        this.sender = sender;
        this.receiver = receiver;
        this.subject = subject;
        this.content = content;
        this.timestamp = timestamp;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public void setReceiver(String receiver) {
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

    public String getSender() {
        return sender;
    }

    public String getReceiver() {
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

    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public static Notification fromJson(String notificationJson) {
        Gson gson = new Gson();
        return gson.fromJson(notificationJson, Notification.class);
    }
}
