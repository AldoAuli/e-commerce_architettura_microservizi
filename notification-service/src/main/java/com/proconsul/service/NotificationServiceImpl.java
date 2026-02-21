package com.proconsul.service;

import com.proconsul.entity.NotificationLog;
import com.proconsul.repository.NotificationLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private NotificationLogRepository repository;

    @Override
    public NotificationLog saveNotification(NotificationLog notification) {
        return repository.save(notification);
    }

    @Override
    public List<NotificationLog> getAllNotifications() {
        return repository.findAll();
    }
}