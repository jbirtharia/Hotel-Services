package com.hotel.exceptions;

import com.hotel.payload.APIResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<APIResponse> handlerResourceNotFoundException(ResourceNotFoundException ex){
        log.error("Resource not found exception : {}", ex.getMessage());
        return new ResponseEntity<APIResponse>(APIResponse.builder().message(ex.getMessage()).success(true)
                .httpStatus(HttpStatus.NOT_FOUND).build(), HttpStatus.NOT_FOUND);
    }

}
