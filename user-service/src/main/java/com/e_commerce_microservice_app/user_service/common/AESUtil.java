package com.e_commerce_microservice_app.user_service.common;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.util.Base64;

public class AESUtil {

    public static SecretKey generateKey() throws Exception{
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(128);
        return keyGen.generateKey();
    }

    public static String encrypt(String data, SecretKey key) throws Exception{
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encryptedData = cipher.doFinal(data.getBytes());
        return Base64.getEncoder().encodeToString(encryptedData);
    }

    public static String decrypt(String encryptedData, SecretKey key) throws Exception{
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decodedBytes = Base64.getDecoder().decode(encryptedData);
        byte[] decryptedData = cipher.doFinal(decodedBytes);
        return new String(decryptedData);
    }
}
