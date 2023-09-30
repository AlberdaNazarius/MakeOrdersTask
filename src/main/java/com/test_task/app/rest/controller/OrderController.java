package com.test_task.app.rest.controller;

import com.test_task.app.dto.RetrieveOrderDto;
import com.test_task.app.dto.SendOrderDto;
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
  public ResponseEntity<List<RetrieveOrderDto>> retrieveAllOrders() {
    final var allOrders = orderService.getAllOrders();
    final var response = orderMapper.orderListToRetrieveOrderDtoList(allOrders);
    log.info("All orders was received");
    return ResponseEntity.ok(response);
  }

  @PostMapping
  public ResponseEntity<RetrieveOrderDto> addOrder(@Valid @RequestBody final SendOrderDto sendOrderDto) {
    final var orderToAdd = orderMapper.sendOrderDtoToOrder(sendOrderDto);
    final var addedOrder = orderService.addOrder(orderToAdd, sendOrderDto);
    final var response = orderMapper.orderToRetrieveOrderDto(addedOrder);
    log.info("Order with id: {} was created", addedOrder.getId());
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }

  @PutMapping("/{orderId}/payed")
  public ResponseEntity<RetrieveOrderDto> markOrderAsPaid(@PathVariable @Nonnull final Long orderId) {
    final var paidOrder = orderService.markAsPaid(orderId);
    final var response = orderMapper.orderToRetrieveOrderDto(paidOrder);
    log.info("Order with id: {} was marked as paid", orderId);
    return ResponseEntity.ok(response);
  }
}
