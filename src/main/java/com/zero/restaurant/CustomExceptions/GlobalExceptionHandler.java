package com.zero.restaurant.CustomExceptions;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value=RequestException.class)
    public ResponseEntity<Object> handleRequestException(RequestException e){
       HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        Exceptions exception = new Exceptions(
                e.getMessage(),
                badRequest,
                ZonedDateTime.now(ZoneId.of("Z"))
        );

        return new ResponseEntity<>(exception,badRequest);
    }


}
