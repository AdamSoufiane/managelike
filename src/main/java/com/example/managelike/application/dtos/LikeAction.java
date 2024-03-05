package com.example.managelike.application.dtos;

/**
 * LikeAction is an enumeration that defines the possible actions that can be performed in a like operation, such as LIKE or UNLIKE.
 * It is designed to be extensible for future actions that may be added.
 */
public enum LikeAction {

    /**
     * Represents the action of liking a post.
     */
    LIKE("Represents the action of liking a post."),

    /**
     * Represents the action of unliking a post.
     */
    UNLIKE("Represents the action of unliking a post.");

    private final String description;

    LikeAction(String description) {
        this.description = description;
    }

    /**
     * Retrieves the description for the LikeAction.
     *
     * @return the description of the LikeAction
     */
    public String getDescription() {
        return description;
    }
}