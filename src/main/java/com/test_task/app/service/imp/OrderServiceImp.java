package com.test_task.app.service.imp;

import com.test_task.app.persistence.entity.Order;
import com.test_task.app.persistence.repository.OrderRepository;
import com.test_task.app.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImp implements OrderService {

  private final OrderRepository orderRepository;

  @Override
  public List<Order> getAllOrders() {
    return orderRepository.findAll();
  }

  @Override
  public Order addOrder(Order order) {
    return orderRepository.save(order);
  }
}
