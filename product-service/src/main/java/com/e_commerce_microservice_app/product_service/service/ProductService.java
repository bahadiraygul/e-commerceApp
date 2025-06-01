package com.e_commerce_microservice_app.product_service.service;

import com.e_commerce_microservice_app.product_service.dto.*;
import com.e_commerce_microservice_app.product_service.entity.Product;
import com.e_commerce_microservice_app.product_service.enums.ExceptionEnum;
import com.e_commerce_microservice_app.product_service.helper.JwtUtil;
import com.e_commerce_microservice_app.product_service.mapper.ProductMapper;
import com.e_commerce_microservice_app.product_service.repository.ProductRepository;
import com.e_commerce_microservice_app.product_service.util.DemoException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final JwtUtil jwtUtil;
    private final ExcelService excelService;

    public void createProduct(ProductDto productDto, String token){
        String jwt = token.replace("Bearer", "");
        String userName = jwtUtil.findUserName(jwt);

        Product product = productMapper.productDtoToProduct(productDto);
        product.setUserName(userName);

        productRepository.save(product);
    }

    public List<ProductDto> getAllProducts(){
        List<Product> products = productRepository.findAll();

        return productMapper.productListToProductDtoList(products);
    }

    public ProductDto getProductById(Long id){
        return productMapper.productToProductDto(productRepository.findById(id)
            .orElseThrow(()-> new DemoException(ProductService.class, ExceptionEnum.PRODUCT_NOT_FOUND)));
    }

    public List<ProductDto> getProductByCategory(String category){
        return productMapper.productListToProductDtoList(productRepository.findByCategory(category));
    }

    public void deleteProductById(Long id){
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new DemoException(ProductService.class, ExceptionEnum.USER_NOT_FOUND));
        productRepository.delete(product);
    }

    public void updateProduct(Long id, ProductUpdateDto productUpdateDto){
        Product product = productRepository.findById(id)
                .orElseThrow(()-> new DemoException(ProductService.class, ExceptionEnum.PRODUCT_NOT_FOUND));

        productMapper.updateProductFromDto(productUpdateDto, product);
        productRepository.save(product);
    }

    public List<ProductDto> getProductDetails(List<Long> productIds){

        List<Product> products = productRepository.findAllById(productIds);

        return productMapper.productListToProductDtoList(products);
    }

    public void updateStock(Long id, StockUpdateDto stockUpdateDto){
        Product product = productRepository.findById(id)
                .orElseThrow(()-> new DemoException(DemoException.class, ExceptionEnum.PRODUCT_NOT_FOUND));

        if(product.getStock() < stockUpdateDto.getQuantity()){
            throw new DemoException(DemoException.class, ExceptionEnum.PRODUCT_OUT_OF_STOCK);
        }

        product.setStock(product.getStock() - stockUpdateDto.getQuantity());
        productRepository.save(product);


    }

    public void increaseStock(Long id, StockUpdateDto stockUpdateDto){
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new DemoException(DemoException.class, ExceptionEnum.PRODUCT_NOT_FOUND));

        product.setStock(product.getStock() + stockUpdateDto.getQuantity());
        productRepository.save(product);
    }

    public ExcelMetadataDto prepareExcelData(){
        final ExcelMetadataDto excelMetadataDto = new ExcelMetadataDto();
        excelMetadataDto.setTableName("Products");
        excelMetadataDto.setHeaders(List.of("Product Name", "Description", "Price", "Stock", "Category","Brand","Sku"));
        final List<ProductDto> products = getAllProducts();
        List<Map<String, String>> metadata = new ArrayList<>();

        for(ProductDto product : products){
            Map<String, String> data = new HashMap<>();
            data.put("Product Name",product.getProductName());
            data.put("Description", product.getDescription());
            data.put("Price", String.valueOf(product.getPrice()));
            data.put("Stock", String.valueOf(product.getStock()));
            data.put("Category",product.getCategory());
            data.put("Brand",product.getBrand());
            data.put("Sku",product.getSku());

        }
        excelMetadataDto.setDatas(metadata);
        return excelMetadataDto;
    }

    public ResourceDto exportExcel(){
        final ResourceDto ResourceDto = excelService.exportExcel(prepareExcelData());
        ResourceDto.setFileName("Products");
        return ResourceDto;
    }
}