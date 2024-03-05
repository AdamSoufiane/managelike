package com.example.managelike.domain.ports;

import com.example.managelike.domain.entities.Like;

/**
 * LikeNotificationOutputPort defines the interface for sending notifications to post owners when their post is liked.
 */
public interface LikeNotificationOutputPort {

    /**
     * Notifies the owner of the post that their content has been liked.
     * @param like The like instance containing details about the like event.
     */
    void notifyPostOwner(Like like);

}