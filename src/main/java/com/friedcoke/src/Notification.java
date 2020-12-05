package com.friedcoke.src;

import java.util.UUID;

public class Notification{
    private String sender;
    private String receiver;
    private UUID id;
    private String content;

    public Notification(UUID id, String content){
        this.id = id;
        this.content = content;
    }

    ￿￿
    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}