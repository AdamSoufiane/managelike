package com.example.managelike.adapters.secondary;

import com.example.managelike.domain.entities.Like;
import com.example.managelike.domain.exceptions.NotificationServiceUnavailableException;
import com.example.managelike.domain.ports.LikeNotificationOutputPort;
import com.example.managelike.infrastructure.services.NotificationService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class LikeNotificationServiceAdapter implements LikeNotificationOutputPort {

    @NonNull
    private final NotificationService notificationService;

    @Override
    public void notifyPostOwner(Like like) {
        String message = createNotificationMessage(like);
        try {
            boolean notificationSent = notificationService.sendNotification(message, String.valueOf(like.getUserId()));
            if (!notificationSent) {
                throw new NotificationServiceUnavailableException("Notification service failed to send message.");
            }
        } catch (NotificationServiceUnavailableException e) {
            throw e;
        }
    }

    private String createNotificationMessage(Like like) {
        return String.format("User %d liked your post %d", like.getUserId(), like.getPostId());
    }
}