package com.e_commerce_microservice_app.api_gateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

public class GatewayConfig {


    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder){
        return builder.routes()

                .route(r -> r.path("/api/**")
                        .or().path("/login/**")
                        .uri("lb://USER-SERVICE")
                )

                .route(r -> r.path("v1/products/**")
                        .uri("lb://PRODUCT-SERVICE")
                )

                .route(r -> r.path("/v1/orders/**")
                        .uri("lb://ORDER-SERVICE")
                )

                .route(r -> r.path("/v1/payments/**")
                        .uri("lb://PAYMENT-SERVICE")
                )
                .build();

    }
}
