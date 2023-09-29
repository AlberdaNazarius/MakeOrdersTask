package com.test_task.app.service.imp;

import com.test_task.app.persistence.entity.Product;
import com.test_task.app.persistence.repository.ProductRepository;
import com.test_task.app.service.ProductService;
import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImp implements ProductService {

  private final ProductRepository productRepository;

  @Override
  public List<Product> getAllAvailableProducts() {
    return productRepository.findAll();
  }

  @Override
  public Product addProduct(@Nonnull Product product) {
    return productRepository.save(product);
  }
}
