package com.example.managelike.domain.entities;

import com.example.managelike.domain.exceptions.DomainException;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import java.time.Instant;

@Getter
@EqualsAndHashCode
@ToString
public class Like {

    private Long likeId;
    private Long postId;
    private Long userId;
    private final Instant createdDate;

    public Like(Long likeId, Long postId, Long userId) {
        validate(likeId, postId, userId);
        this.likeId = likeId;
        this.postId = postId;
        this.userId = userId;
        this.createdDate = Instant.now();
    }

    private void validate(Long likeId, Long postId, Long userId) {
        if (likeId == null || likeId < 0 || postId == null || postId < 0 || userId == null || userId < 0) {
            throw new DomainException("IDs must not be null or negative");
        }
    }

    // Additional methods that might alter the state of the IDs should invoke validate here.

}