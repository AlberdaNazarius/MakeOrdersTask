package com.test_task.app.rest.controller;

import com.test_task.app.dto.OrderDto;
import com.test_task.app.service.OrderService;
import com.test_task.app.service.mapper.OrderMapper;
import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/order")
@Validated
public class OrderController {
  private final OrderService orderService;
  private final OrderMapper orderMapper;

  @GetMapping
  public ResponseEntity<List<OrderDto>> retrieveAllOrders() {
    final var allOrders = orderService.getAllOrders();
    final var response = orderMapper.orderListToOrderDtoList(allOrders);
    return ResponseEntity.ok(response);
  }

  @PostMapping
  public ResponseEntity<OrderDto> addOrder(@Nonnull @RequestBody OrderDto orderDto) {
    final var orderToAdd = orderMapper.orderDtoToOrder(orderDto);
    final var addedOrder = orderService.addOrder(orderToAdd);
    final var response = orderMapper.orderToOrderDto(addedOrder);
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }
}
