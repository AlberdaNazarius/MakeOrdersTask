package com.test_task.app.service.mapper;

import com.test_task.app.dto.RetrieveOrderDto;
import com.test_task.app.dto.SendOrderDto;
import com.test_task.app.persistence.entity.Order;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {
  List<RetrieveOrderDto> orderListToRetrieveOrderDtoList(List<Order> orders);
  Order sendOrderDtoToOrder(SendOrderDto sendOrderDto);
  RetrieveOrderDto orderToRetrieveOrderDto(Order order);
}
