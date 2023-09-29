package com.test_task.app.service;

import com.test_task.app.persistence.entity.Order;

import java.util.List;

public interface OrderService {
  List<Order> getAllOrders();
  Order addOrder(Order order);
}
