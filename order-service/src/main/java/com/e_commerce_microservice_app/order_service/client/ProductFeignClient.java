package com.e_commerce_microservice_app.order_service.client;

import com.e_commerce_microservice_app.order_service.dto.ProductDTO;
import com.e_commerce_microservice_app.order_service.dto.StockUpdateDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "product-service", url = "http://localhost:8888/v1/products")
public interface ProductFeignClient {

    @GetMapping("/details")
    List<ProductDTO> getProductDetails(@RequestParam("productIds") List<Long> productIds);

    @GetMapping("/product/{id}")
    ProductDTO getProductById(@PathVariable("id") Long productId);

    @PutMapping("/{id}/stock")
    void updateStock(@PathVariable Long id, @RequestBody StockUpdateDto stockUpdateDto);

    @PutMapping("/{id}/increase-stock")
    void increaseStock(@PathVariable Long id, @RequestBody StockUpdateDto stockUpdateDto);
}
