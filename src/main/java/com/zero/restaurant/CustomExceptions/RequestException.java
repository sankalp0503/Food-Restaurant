package com.zero.restaurant.CustomExceptions;

public class RequestException extends RuntimeException{

    public RequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public RequestException(String message) {
        super(message);
    }
}
