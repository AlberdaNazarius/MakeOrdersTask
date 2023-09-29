package com.test_task.app.service;

import com.test_task.app.persistence.entity.Product;
import jakarta.annotation.Nonnull;

import java.util.List;

public interface ProductService {
  List<Product> getAllAvailableProducts();
  Product addProduct(@Nonnull Product product);

}
