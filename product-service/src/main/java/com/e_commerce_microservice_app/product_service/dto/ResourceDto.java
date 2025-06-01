package com.e_commerce_microservice_app.product_service.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;

@Getter
@Setter
public class ResourceDto {
    private Resource resource;
    private MediaType mediaType;
    private String fileName;
}
