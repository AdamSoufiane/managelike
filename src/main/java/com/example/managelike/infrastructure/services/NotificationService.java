package com.example.managelike.infrastructure.services;

import com.example.managelike.domain.exceptions.NotificationServiceUnavailableException;

/**
 * NotificationService interface provides an abstraction over the actual notification sending mechanism.
 * It defines the contract for services that are capable of sending notifications, such as sendNotification method.
 */
public interface NotificationService {

    /**
     * Attempts to send a notification message to a specified recipient.
     * Returns true if the notification is sent successfully, false if there is a failure in the notification delivery process,
     * such as network issues or invalid recipient details.
     *
     * @param message the notification message to be sent
     * @param recipient the recipient's details
     * @return true if the notification was sent successfully, false otherwise
     * @throws NotificationServiceUnavailableException if the notification service is unavailable
     */
    boolean sendNotification(String message, String recipient) throws NotificationServiceUnavailableException;

}