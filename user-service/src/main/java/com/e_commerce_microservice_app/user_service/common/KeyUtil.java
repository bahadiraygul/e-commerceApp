package com.e_commerce_microservice_app.user_service.common;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class KeyUtil {

    public static SecretKey getSecretKeyFromString(String key){
        byte[] decodedKey = Base64.getDecoder().decode(key);
        return new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");
    }
}
