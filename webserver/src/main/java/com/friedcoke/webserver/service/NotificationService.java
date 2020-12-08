package com.friedcoke.webserver.service;

import com.friedcoke.rmi.FriedCokeMetadataClient;
import com.friedcoke.webserver.model.Notification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotificationService {

    private final FriedCokeMetadataClient friedCokeMetadataClient = FriedCokeMetadataClient.getInstance();

    public int addNotification(Notification notification) {
        return friedCokeMetadataClient.addNotification(notification.toJson());
    }

    public List<Notification> getAllNotifications() {
        List<String> notificationJsonList = friedCokeMetadataClient.getAllNotifications();
        List<Notification> notificationList = notificationJsonList.stream()
                .map(notificationJson -> Notification.fromJson(notificationJson))
                .collect(Collectors.toList());
        return notificationList;
    }
}
