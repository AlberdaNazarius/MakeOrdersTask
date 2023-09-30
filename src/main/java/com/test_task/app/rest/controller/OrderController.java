package com.test_task.app.rest.controller;

import com.test_task.app.dto.OrderDto;
import com.test_task.app.service.OrderService;
import com.test_task.app.service.mapper.OrderMapper;
import jakarta.annotation.Nonnull;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/order")
@Validated
@Slf4j
public class OrderController {
  private final OrderService orderService;
  private final OrderMapper orderMapper;

  @GetMapping("orders")
  public ResponseEntity<List<OrderDto>> retrieveAllOrders() {
    final var allOrders = orderService.getAllOrders();
    final var response = orderMapper.orderListToOrderDtoList(allOrders);
    log.info("All orders was received");
    return ResponseEntity.ok(response);
  }

  @PostMapping
  public ResponseEntity<OrderDto> addOrder(@Valid @RequestBody final OrderDto orderDto) {
    final var orderToAdd = orderMapper.orderDtoToOrder(orderDto);
    final var addedOrder = orderService.addOrder(orderToAdd);
    final var response = orderMapper.orderToOrderDto(addedOrder);
    log.info("Order with id: {} was created", addedOrder.getId());
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }

  @PutMapping("/{orderId}/payed")
  public ResponseEntity<OrderDto> markOrderAsPaid(@PathVariable @Nonnull final Long orderId) {
    final var paidOrder = orderService.markAsPaid(orderId);
    final var response = orderMapper.orderToOrderDto(paidOrder);
    log.info("Order with id: {} was marked as paid", orderId);
    return ResponseEntity.ok(response);
  }
}
