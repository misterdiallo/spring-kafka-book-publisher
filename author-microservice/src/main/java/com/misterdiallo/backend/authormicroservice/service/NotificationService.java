package com.misterdiallo.backend.authormicroservice.service;

import com.misterdiallo.backend.authormicroservice.domain.Notification;

public interface NotificationService {
    void publishNotification(Notification notification);
}