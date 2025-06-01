package com.e_commerce_microservice_app.user_service.common;

public class EncryptionException extends RuntimeException {
    public EncryptionException(String message) {
        super(message);
    }

    public EncryptionException(String message, Throwable cauese) {
        super(message, cauese);
    }

    public EncryptionException(Throwable cause) {
        super(cause);
    }
}
