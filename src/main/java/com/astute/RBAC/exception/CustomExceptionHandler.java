package com.astute.RBAC.exception;

import java.util.Date;

import com.astute.RBAC.dto.response.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler({ AuthenticatedFailureException.class })
    public ResponseEntity<?> handleAuthenticateFailureException(AuthenticatedFailureException exception) {
        ErrorResponseDTO errorResponse = new ErrorResponseDTO("Authenticated Failure", exception.getMessage(),
                new Date());
        return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(401));
    }

    @ExceptionHandler({ UnauthorizedException.class })
    public ResponseEntity<?> handleUnauthorizedException(UnauthorizedException exception) {
        ErrorResponseDTO errorResponse = new ErrorResponseDTO(
                "Unauthorized", exception.getMessage(), new Date());
        return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(403));
    }
}