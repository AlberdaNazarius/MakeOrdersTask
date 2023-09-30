package com.test_task.app.service.mapper;

import com.test_task.app.dto.RetrieveOrderProductDto;
import com.test_task.app.dto.SendOrderProductDto;
import com.test_task.app.persistence.entity.OrderProduct;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderProductMapper {
  OrderProduct sendOrderProductDtoToOrderProduct(SendOrderProductDto sendOrderProductDto);

  RetrieveOrderProductDto orderProductToRetrieveOrderProductDto(OrderProduct orderProduct);
}
