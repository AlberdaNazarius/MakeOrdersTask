package com.test_task.app.service.imp;

import com.test_task.app.exception.OrderNotFoundException;
import com.test_task.app.persistence.entity.Order;
import com.test_task.app.persistence.repository.OrderRepository;
import com.test_task.app.service.OrderService;
import jakarta.annotation.Nonnull;
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
  public Order addOrder(@Nonnull Order order) {
    return orderRepository.save(order);
  }

  @Override
  public Order markAsPaid(@Nonnull Long orderId) {
    var orderToChange = orderRepository.findById(orderId);
    if (orderToChange.isEmpty()) {
      throw new OrderNotFoundException("Order with such id was not found");
    }
    orderToChange.get().setPaid(true);
    return orderRepository.save(orderToChange.get());
  }
}
