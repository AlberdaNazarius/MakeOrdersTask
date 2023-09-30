package com.test_task.app.service.imp;

import com.test_task.app.dto.SendOrderDto;
import com.test_task.app.persistence.OrderProductId;
import com.test_task.app.persistence.entity.OrderProduct;
import com.test_task.app.persistence.repository.OrderProductRepository;
import com.test_task.app.persistence.repository.OrderRepository;
import com.test_task.app.persistence.repository.ProductRepository;
import com.test_task.app.service.OrderProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderProductServiceImp implements OrderProductService {

  private final OrderRepository orderRepository;
  private final ProductRepository productRepository;
  private final OrderProductRepository orderProductRepository;

  @Override
  public Set<OrderProduct> addOrderProducts(SendOrderDto orderDto, Long orderId) {
    final var orderProducts = getOrderProducts(orderDto, orderId);
    orderProductRepository.saveAll(orderProducts);
    return orderProducts;
  }

  private Set<OrderProduct> getOrderProducts(SendOrderDto sendOrderDto, Long orderId) {
    return sendOrderDto.getProducts()
            .stream()
            .map(dto -> {
              final var productId = dto.getProductId();
              final var orderProductId = OrderProductId.builder()
                      .orderId(orderId)
                      .productId(productId)
                      .build();
              return OrderProduct.builder()
                      .id(orderProductId)
                      .units(dto.getUnits())
                      .order(orderRepository.findById(orderId).get())
                      .product(productRepository.findById(productId).get())
                      .build();
            }).collect(Collectors.toSet());
  }
}
