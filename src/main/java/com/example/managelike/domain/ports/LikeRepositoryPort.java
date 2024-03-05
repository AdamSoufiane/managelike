package com.example.managelike.domain.ports;

import com.example.managelike.domain.entities.Like;
import com.example.managelike.domain.exceptions.DomainException;
import java.util.Optional;

/**
 * LikeRepositoryPort defines the operations that can be performed on Like entities,
 * such as saving a like or retrieving the like count for a post.
 */
public interface LikeRepositoryPort {

    Like saveLike(Like like) throws DomainException;

    Optional<Like> findLikeByPostIdAndUserId(Long postId, Long userId) throws DomainException;

    void deleteLike(Like like) throws DomainException;

    long countLikesByPostId(Long postId) throws DomainException;
}