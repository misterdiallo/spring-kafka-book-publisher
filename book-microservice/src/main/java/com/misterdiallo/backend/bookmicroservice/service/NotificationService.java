package com.misterdiallo.backend.bookmicroservice.service;

import com.misterdiallo.backend.bookmicroservice.domain.Notification;

public interface NotificationService {
    void publishNotification(Notification notification);
}