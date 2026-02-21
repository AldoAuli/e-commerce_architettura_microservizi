package com.proconsul.service;


import com.proconsul.entity.NotificationLog;

import java.util.List;

public interface NotificationService {

    NotificationLog saveNotification(NotificationLog notification);

    List<NotificationLog> getAllNotifications();
}
