package com.example.managelike.application.use_cases;

import com.example.managelike.application.dtos.LikeRequest;
import com.example.managelike.application.dtos.LikeResponse;
import com.example.managelike.application.exceptions.ValidationException;
import com.example.managelike.domain.exceptions.DomainException;
import com.example.managelike.domain.ports.LikeRepositoryPort;
import com.example.managelike.domain.services.LikeDomainService;
import com.example.managelike.infrastructure.exceptions.InfrastructureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * LikeUseCaseService orchestrates the process of liking or unliking a post.
 * It uses the LikeDomainService to perform domain-specific business logic
 * and interacts with the LikeRepositoryPort to persist changes.
 */
@Service
public class LikeUseCaseService {

    private final LikeDomainService likeDomainService;
    private final LikeRepositoryPort likeRepositoryPort;
    private final PlatformTransactionManager transactionManager;
    private final Validator validator;
    private final Logger logger = LoggerFactory.getLogger(LikeUseCaseService.class);

    public LikeUseCaseService(LikeDomainService likeDomainService,
                              LikeRepositoryPort likeRepositoryPort,
                              PlatformTransactionManager transactionManager,
                              Validator validator) {
        this.likeDomainService = likeDomainService;
        this.likeRepositoryPort = likeRepositoryPort;
        this.transactionManager = transactionManager;
        this.validator = validator;
    }

    public LikeResponse likePost(LikeRequest likeRequest) throws DomainException, InfrastructureException {
        validate(likeRequest);
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setName("likePostTransaction");
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = transactionManager.getTransaction(def);

        try {
            likeDomainService.incrementLikeCount(likeRequest.getPostId(), likeRequest.getUserId());
            long likesCount = likeRepositoryPort.countLikesByPostId(likeRequest.getPostId());
            transactionManager.commit(status);
            return new LikeResponse(likeRequest.getPostId(), likeRequest.getUserId(), LikeResponse.LikeAction.LIKE, (int) likesCount);
        } catch (DomainException | InfrastructureException e) {
            transactionManager.rollback(status);
            throw e;
        }
    }

    public LikeResponse unlikePost(LikeRequest likeRequest) throws DomainException, InfrastructureException {
        validate(likeRequest);
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setName("unlikePostTransaction");
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = transactionManager.getTransaction(def);

        try {
            likeDomainService.decrementLikeCount(likeRequest.getPostId(), likeRequest.getUserId());
            long likesCount = likeRepositoryPort.countLikesByPostId(likeRequest.getPostId());
            transactionManager.commit(status);
            return new LikeResponse(likeRequest.getPostId(), likeRequest.getUserId(), LikeResponse.LikeAction.UNLIKE, (int) likesCount);
        } catch (DomainException | InfrastructureException e) {
            transactionManager.rollback(status);
            throw e;
        }
    }

    private void validate(LikeRequest likeRequest) throws ValidationException {
        if (!validator.supports(likeRequest.getClass())) {
            throw new ValidationException("Validator does not support the LikeRequest class");
        }
        Errors errors = new BeanPropertyBindingResult(likeRequest, "likeRequest");
        validator.validate(likeRequest, errors);

        if (errors.hasErrors()) {
            throw new ValidationException("Validation failed for LikeRequest", errors);
        }
    }
}
