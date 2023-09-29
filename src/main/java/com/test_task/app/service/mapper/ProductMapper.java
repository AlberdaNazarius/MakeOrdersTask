package com.test_task.app.service.mapper;

import com.test_task.app.dto.ProductDto;
import com.test_task.app.persistence.entity.Product;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {
  ProductDto productToProductDto(Product product);
  Product productDtoToProduct(ProductDto productDto);
  List<ProductDto> productListToProductDtoList(List<Product> products);
}
