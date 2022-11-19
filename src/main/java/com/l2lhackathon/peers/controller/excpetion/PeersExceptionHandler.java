package com.l2lhackathon.peers.controller.excpetion;

import java.time.Instant;

import com.l2lhackathon.peers.exception.PeersCoreException;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class PeersExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(PeersCoreException.class)
    protected ResponseEntity<?> handleCoreException(PeersCoreException exception) {
        HttpStatus status = HttpStatus.resolve(exception.getCode());
        if (status == null) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return handle(exception, status);
    }

    @ExceptionHandler(Throwable.class)
    protected ResponseEntity<?> handleJavaException(Throwable exception) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        return handle(exception, status);
    }

    private ResponseEntity<?> handle(Throwable exception, HttpStatus status) {
        return ResponseEntity
                .status(status)
                .contentType(MediaType.APPLICATION_JSON)
                .body(
                        new ErrorResponseDto(
                                Instant.now(),
                                status.value(),
                                exception.getMessage(),
                                ExceptionUtils.getStackTrace(exception)
                        )
                );
    }

}
