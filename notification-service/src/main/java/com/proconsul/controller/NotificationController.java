package com.proconsul.controller;

import com.proconsul.entity.NotificationLog;
import com.proconsul.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/api/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @GetMapping
    public List<NotificationLog> getAllNotifications() {
        return notificationService.getAllNotifications();
    }
}
