package com.test_task.app.rest.controller;

import com.test_task.app.dto.ProductDto;
import com.test_task.app.service.ProductService;
import com.test_task.app.service.mapper.ProductMapper;
import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product")
@Validated
public class ProductController {

  private final ProductService productService;
  private final ProductMapper productMapper;

  @GetMapping("/products")
  public ResponseEntity<List<ProductDto>> getAllAvailableProducts() {
    final var products = productService.getAllAvailableProducts();
    final var response = productMapper.productListToProductDtoList(products);
    return ResponseEntity.ok(response);
  }

  @PostMapping
  public ResponseEntity<ProductDto> addProduct(@Nonnull @RequestBody ProductDto productDto) {
    final var productToAdd = productMapper.productDtoToProduct(productDto);
    final var addedProduct = productService.addProduct(productToAdd);
    final var response = productMapper.productToProductDto(addedProduct);
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }
}
