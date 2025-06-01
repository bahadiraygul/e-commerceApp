package com.e_commerce_microservice_app.user_service.config;

import com.e_commerce_microservice_app.user_service.common.AESUtil;
import org.springframework.context.annotation.Bean;

import javax.crypto.SecretKey;

public class AESConfig {

    @Bean
    public SecretKey secretKey() throws Exception{
        return AESUtil.generateKey();
    }
}
