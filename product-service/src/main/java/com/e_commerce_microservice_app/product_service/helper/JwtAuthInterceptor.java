package com.e_commerce_microservice_app.product_service.helper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class JwtAuthInterceptor implements HandlerInterceptor {

    private final JwtUtil jwtUtil;

    public JwtAuthInterceptor(JwtUtil jwtUtil){
        this.jwtUtil = jwtUtil;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
        String authHeader = request.getHeader("Authorization");

        if(authHeader == null || !authHeader.startsWith("Bearer")){
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"JWT Token Gerekli!");
            return false;
        }

        String token = authHeader.substring(7);

        if(!jwtUtil.tokenControl(token)){
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"JWT Token error!");
            return false;
        }

        String username = jwtUtil.findUserName(token);
        System.out.println("İstek yapan kullanıcı : " + username);

        return true;
    }

}
