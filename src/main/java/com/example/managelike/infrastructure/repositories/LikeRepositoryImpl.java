package com.example.managelike.infrastructure.repositories;

import com.example.managelike.domain.entities.Like;
import com.example.managelike.domain.exceptions.DomainException;
import com.example.managelike.infrastructure.exceptions.InfrastructureException;
import com.example.managelike.domain.ports.LikeRepositoryPort;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Repository
public class LikeRepositoryImpl implements LikeRepositoryPort, Serializable {

    private static final long serialVersionUID = 1L;
    private final EntityManager entityManager;

    public LikeRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public Like saveLike(Like like) throws DomainException {
        try {
            entityManager.persist(like);
            return like;
        } catch (PersistenceException e) {
            throw new InfrastructureException("Failed to save like", e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Like> findLikeByPostIdAndUserId(Long postId, Long userId) throws DomainException {
        try {
            List<Like> likes = entityManager.createQuery("SELECT l FROM Like l WHERE l.postId = :postId AND l.userId = :userId", Like.class)
                .setParameter("postId", postId)
                .setParameter("userId", userId)
                .getResultList();
            return likes.stream().findFirst();
        } catch (PersistenceException e) {
            throw new InfrastructureException("Failed to find like by post and user ID", e);
        }
    }

    @Override
    @Transactional
    public void deleteLike(Like like) throws DomainException {
        try {
            entityManager.remove(entityManager.contains(like) ? like : entityManager.merge(like));
        } catch (PersistenceException e) {
            throw new InfrastructureException("Failed to delete like", e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public long countLikesByPostId(Long postId) throws DomainException {
        try {
            return entityManager.createQuery("SELECT COUNT(l) FROM Like l WHERE l.postId = :postId", Long.class)
                .setParameter("postId", postId)
                .getSingleResult();
        } catch (PersistenceException e) {
            throw new InfrastructureException("Failed to count likes by post ID", e);
        }
    }
}
