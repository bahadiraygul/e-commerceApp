package com.e_commerce_microservice_app.product_service.mapper;

import com.e_commerce_microservice_app.product_service.dto.ProductDto;
import com.e_commerce_microservice_app.product_service.dto.ProductUpdateDto;
import com.e_commerce_microservice_app.product_service.entity.Product;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductDto productToProductDto(Product product);

    Product productDtoToProduct(ProductDto productDto);

    List<ProductDto> productListToProductDtoList(List<Product> productList);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateProductFromDto(ProductUpdateDto dto, @MappingTarget Product product);

}
