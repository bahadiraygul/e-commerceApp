package com.e_commerce_microservice_app.product_service.controller;

import com.e_commerce_microservice_app.product_service.client.UserClient;
import com.e_commerce_microservice_app.product_service.dto.ProductDto;
import com.e_commerce_microservice_app.product_service.dto.ProductUpdateDto;
import com.e_commerce_microservice_app.product_service.dto.ResourceDto;
import com.e_commerce_microservice_app.product_service.dto.StockUpdateDto;
import com.e_commerce_microservice_app.product_service.entity.Product;
import com.e_commerce_microservice_app.product_service.enums.MessageEnum;
import com.e_commerce_microservice_app.product_service.service.ExcelService;
import com.e_commerce_microservice_app.product_service.service.ProductService;
import com.e_commerce_microservice_app.product_service.util.BaseResponse;
import com.e_commerce_microservice_app.product_service.util.ResponseHandler;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/products")
public class ProductController {

    private final ProductService productService;
    private final UserClient userClient;
    private final ExcelService excelService;
    private final ResponseHandler responseHandler;

    public ProductController(ProductService productService, UserClient userClient, ExcelService excelService, ResponseHandler responseHandler) {
        this.productService = productService;
        this.userClient = userClient;
        this.excelService = excelService;
        this.responseHandler = responseHandler;
    }

    @PostMapping
    public BaseResponse<ProductDto> createProduct(@RequestBody ProductDto productDto, @RequestHeader ("Authorization") String token){
        productService.createProduct(productDto, token);
        return ResponseHandler.responseEntityWrapper(HttpStatus.CREATED, MessageEnum.PRODUCT_CREATED);
    }

    @GetMapping
    public BaseResponse<List<ProductDto>> getAllProducts(){
        return ResponseHandler.responseEntityWrapper(HttpStatus.OK, productService.getAllProducts(),MessageEnum.PRODUCTS_FOUND);
    }

    @GetMapping("/product/{id}")
    public ProductDto getProductById(@PathVariable Long id){
        return productService.getProductById(id);
    }

    @GetMapping("/{category}")
    public BaseResponse<List<ProductDto>> getProductsByCategory(@PathVariable String category){
        return ResponseHandler.responseEntityWrapper(HttpStatus.OK, productService.getProductByCategory(category));
    }

    @DeleteMapping("/{id}")
    public BaseResponse<String> deleteProduct(@PathVariable Long id){
        productService.deleteProductById(id);
        return ResponseHandler.responseEntityWrapper(HttpStatus.OK, MessageEnum.PRODUCT_DELETED);
    }

    @PutMapping("/{id}")
    public BaseResponse<String> updateProduct(@PathVariable Long id, @RequestBody ProductUpdateDto productUpdateDto){
        productService.updateProduct(id, productUpdateDto);
        return ResponseHandler.responseEntityWrapper(HttpStatus.OK, MessageEnum.PRODUCT_UPDATED);
    }

    @GetMapping("/details")
    public BaseResponse<List<ProductDto>> getProductDetails(@RequestParam List<Long> productIds){
        List<ProductDto> productDtos =  productService.getProductDetails(productIds);
        return ResponseHandler.responseEntityWrapper(HttpStatus.OK, productDtos);
    }

    @PutMapping("/{id}/stock")
    public BaseResponse<String> updateStock(@PathVariable Long id, @RequestBody StockUpdateDto stockUpdateDto){
        productService.updateStock(id, stockUpdateDto);
        return ResponseHandler.responseEntityWrapper(HttpStatus.OK, MessageEnum.STOCK_UPDATED);
    }

    @PutMapping("/{id}/increase-stock")
    public BaseResponse<String> increaseStock(@PathVariable Long id, @RequestBody StockUpdateDto stockUpdateDto){
        productService.increaseStock(id, stockUpdateDto);
        return ResponseHandler.responseEntityWrapper(HttpStatus.OK, MessageEnum.STOCK_INCREASED);
    }

    @GetMapping("/export")
    public ResponseEntity<Resource> exportProducts(){
        ResourceDto resourceDto = productService.exportExcel();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Disposition", "attachment; filename=" + resourceDto.getFileName() + ".xlsx");

        return ResponseEntity.ok().contentType(resourceDto.getMediaType())
                .headers(httpHeaders).body(resourceDto.getResource());
    }


}
