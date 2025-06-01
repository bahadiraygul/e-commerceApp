package com.e_commerce_microservice_app.order_service.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

@Component
public class FeignRequestInterceptor implements RequestInterceptor {

    private final HttpServletRequest request;

    public FeignRequestInterceptor(HttpServletRequest request){
        this.request = request;
    }

    @Override
    public void apply(RequestTemplate template) {
        String jwtToken = getJwtToken(request);
        if(jwtToken != null){
            template.header("Authorization", "Bearer " + jwtToken);
        }
    }

    private String getJwtToken(HttpServletRequest request){
        String token = request.getHeader("Authorization");
        if(token != null && token.startsWith("Bearer ")){
            return token.substring(7);
        }
        return null;
    }
}
