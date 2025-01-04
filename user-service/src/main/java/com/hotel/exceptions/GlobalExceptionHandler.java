package com.hotel.exceptions;

import com.hotel.payload.APIResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<APIResponse> handlerResourceNotFoundException(ResourceNotFoundException ex){
        return new ResponseEntity<APIResponse>(APIResponse.builder().message(ex.getMessage()).success(true)
                .httpStatus(HttpStatus.NOT_FOUND).build(), HttpStatus.NOT_FOUND);
    }

}
