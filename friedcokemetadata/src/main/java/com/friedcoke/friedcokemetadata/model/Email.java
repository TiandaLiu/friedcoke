package com.friedcoke.friedcokemetadata.model;

import com.google.gson.Gson;

import java.util.UUID;

public class Email {
    private UUID emailId;
    private String senderId;
    private String receiverId;
    private String subject;
    private String content;
    private String timeStamp;

    public Email(UUID emailId, String senderId, String receiverId, String subject, String content, String timeStamp) {
        this.emailId = emailId;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.subject = subject;
        this.content = content;
        this.timeStamp = timeStamp;
    }

    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public static Email fromJson(String emailJson) {
        Gson gson = new Gson();
        return gson.fromJson(emailJson, Email.class);
    }
}
