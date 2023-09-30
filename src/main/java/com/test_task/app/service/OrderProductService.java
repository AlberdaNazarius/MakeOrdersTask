package com.test_task.app.service;

import com.test_task.app.dto.SendOrderDto;
import com.test_task.app.persistence.entity.OrderProduct;

import java.util.Set;

public interface OrderProductService {
  Set<OrderProduct> addOrderProducts(SendOrderDto orderDto, Long orderId);
}
