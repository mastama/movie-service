package com.yolifay.movieservice.common;

import com.yolifay.movieservice.exception.BadRequestException;
import com.yolifay.movieservice.exception.ConflictException;
import com.yolifay.movieservice.exception.DataNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @Value("${service.id}")
    private String serviceId;

    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<ResponseService> handleDataNotFound(DataNotFoundException e) {
        return ResponseEntity.ok(
                ResponseUtil.setResponse(
                        HttpStatus.NOT_FOUND.value(),
                        serviceId,
                        Constants.RESPONSE.ACCOUNT_NOT_FOUND,
                        e.getMessage()
                )
        );
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ResponseService> handleBadRequest(BadRequestException e) {
        return ResponseEntity.ok(
                ResponseUtil.setResponse(
                        HttpStatus.BAD_REQUEST.value(),
                        serviceId,
                        Constants.RESPONSE.BAD_REQUEST,
                        e.getMessage()
                )
        );
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<ResponseService> handleConflict(ConflictException e) {
        return ResponseEntity.ok(
                ResponseUtil.setResponse(
                        HttpStatus.CONFLICT.value(),
                        serviceId,
                        Constants.RESPONSE.DATA_EXISTS,
                        e.getMessage()
                )
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseService> handleGeneral(Exception e) {
        return ResponseEntity.ok(
                ResponseUtil.setResponse(
                        HttpStatus.INTERNAL_SERVER_ERROR.value(),
                        serviceId,
                        Constants.RESPONSE.HTTP_INTERNAL_ERROR,
                        e.getMessage()
                )
        );
    }
}
