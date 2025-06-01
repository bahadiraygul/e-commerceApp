package com.e_commerce_microservice_app.payment_service.helper;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final JwtAuthInterceptor jwtAuthInterceptor;

    public WebConfig(JwtAuthInterceptor jwtAuthInterceptor){
        this.jwtAuthInterceptor = jwtAuthInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(jwtAuthInterceptor)
                .addPathPatterns("/v1/products/**");
    }

}
