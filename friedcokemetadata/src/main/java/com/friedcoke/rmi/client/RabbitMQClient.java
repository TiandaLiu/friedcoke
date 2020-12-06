package com.friedcoke.rmi.client;

import java.util.List;

public class RabbitMQClient {

    public int sendEmail(String emailId, String emailJson) {
        return 1;
    }

    // return a list of emailJson
    public List<String> getEmailsByReceiver(String receiverId) {
        return null;
    }

    // return a list of emailJson
    public List<String> getAllEmails() {
        return null;
    }}