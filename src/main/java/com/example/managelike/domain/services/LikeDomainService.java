package com.example.managelike.domain.services;

import com.example.managelike.domain.entities.Like;
import com.example.managelike.domain.exceptions.DomainException;
import com.example.managelike.domain.ports.LikeRepositoryPort;
import lombok.RequiredArgsConstructor;
import java.util.Optional;

@RequiredArgsConstructor
public class LikeDomainService {

    private final LikeRepositoryPort likeRepositoryPort;

    public void incrementLikeCount(Long postId, Long userId) throws DomainException {
        validateId(postId, "post");
        validateId(userId, "user");
        Like like = new Like(null, postId, userId);
        likeRepositoryPort.saveLike(like);
    }

    public void decrementLikeCount(Long postId, Long userId) throws DomainException {
        validateId(postId, "post");
        validateId(userId, "user");
        Optional<Like> like = likeRepositoryPort.findLikeByPostIdAndUserId(postId, userId);
        like.ifPresent(likeRepositoryPort::deleteLike);
    }

    public boolean checkLikeExists(Long postId, Long userId) throws DomainException {
        validateId(postId, "post");
        validateId(userId, "user");
        return likeRepositoryPort.findLikeByPostIdAndUserId(postId, userId).isPresent();
    }

    private void validateId(Long id, String idType) throws DomainException {
        if (id == null || id < 0) {
            throw new DomainException("Invalid " + idType + " ID: " + id);
        }
    }
}
