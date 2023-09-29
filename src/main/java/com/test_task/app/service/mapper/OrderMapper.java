package com.test_task.app.service.mapper;

import com.test_task.app.dto.OrderDto;
import com.test_task.app.persistence.entity.Order;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {
  Order orderDtoToOrder(OrderDto orderDto);
  OrderDto orderToOrderDto(Order order);
  List<OrderDto> orderListToOrderDtoList(List<Order> orders);
}
