package com.example.managelike.adapters.primary;

import com.example.managelike.application.dtos.LikeRequest;
import com.example.managelike.application.dtos.LikeResponse;
import com.example.managelike.application.exceptions.ApplicationException;
import com.example.managelike.application.exceptions.ValidationException;
import com.example.managelike.application.use_cases.LikeUseCaseService;
import com.example.managelike.domain.exceptions.DomainException;
import com.example.managelike.domain.exceptions.InfrastructureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
public class LikeController {

    private final LikeUseCaseService likeUseCaseService;

    public LikeController(LikeUseCaseService likeUseCaseService) {
        this.likeUseCaseService = likeUseCaseService;
    }

    @PostMapping("/like")
    public ResponseEntity<LikeResponse> likePost(@Valid @RequestBody LikeRequest likeRequest) {
        try {
            LikeResponse response = likeUseCaseService.likePost(likeRequest);
            return ResponseEntity.ok(response);
        } catch (ApplicationException | DomainException | InfrastructureException e) {
            return handleException(e);
        }
    }

    @PostMapping("/unlike")
    public ResponseEntity<LikeResponse> unlikePost(@Valid @RequestBody LikeRequest likeRequest) {
        try {
            LikeResponse response = likeUseCaseService.unlikePost(likeRequest);
            return ResponseEntity.ok(response);
        } catch (ApplicationException | DomainException | InfrastructureException e) {
            return handleException(e);
        }
    }

    @ExceptionHandler(DomainException.class)
    public ResponseEntity<String> handleDomainException(DomainException exception) {
        log.error("Domain exception occurred", exception);
        return ResponseEntity.badRequest().body(exception.getMessage());
    }

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<String> handleApplicationException(ApplicationException exception) {
        log.error("Application exception occurred", exception);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
    }

    @ExceptionHandler(InfrastructureException.class)
    public ResponseEntity<String> handleInfrastructureException(InfrastructureException exception) {
        log.error("Infrastructure exception occurred", exception);
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(exception.getMessage());
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<String> handleValidationException(ValidationException exception) {
        log.error("Validation exception occurred", exception);
        return ResponseEntity.badRequest().body(exception.getErrors().toString());
    }

    private ResponseEntity<String> handleException(Exception e) {
        if (e instanceof DomainException) {
            return handleDomainException((DomainException) e);
        } else if (e instanceof ApplicationException) {
            return handleApplicationException((ApplicationException) e);
        } else if (e instanceof InfrastructureException) {
            return handleInfrastructureException((InfrastructureException) e);
        } else if (e instanceof ValidationException) {
            return handleValidationException((ValidationException) e);
        } else {
            log.error("Unexpected exception occurred", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred");
        }
    }
}
