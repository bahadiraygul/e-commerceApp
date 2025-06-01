package com.e_commerce_microservice_app.product_service.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class ExcelMetadataDto {
    private String tableName;
    private List<String> headers;
    private List<Map<String, String>> datas;
}
