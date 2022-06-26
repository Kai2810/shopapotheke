package com.shopapotheke.exception;

public class ClientApiException extends RuntimeException {
    public ClientApiException(Throwable throwable) {
        super(throwable);
    }

    public ClientApiException(String message) {
        super(message);
    }
}
