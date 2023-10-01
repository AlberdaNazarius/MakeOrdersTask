package com.test_task.app.dto;

import com.test_task.app.persistence.entity.OrderProduct;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Value;
import lombok.experimental.FieldDefaults;

/**
 * {@code SendOrderProductDto} class contains data for creating new {@link OrderProduct}.
 * <p>
 *   Basically this data needed for creating new relationship between {@code Order} and {@code Product}. Additional
 *   data units indicates what amount of some product client want to buy.
 * </p>
 *
 * @see SendOrderDto
 */
@Value
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SendOrderProductDto {
  Long productId;
  Integer units;
}
