package com.friedcoke.webserver.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/notification")
@CrossOrigin()
public class NotificationController {

    @GetMapping
    public String hello() {
        return "{\"string\":\"hello from web server\"}";
    }
}
