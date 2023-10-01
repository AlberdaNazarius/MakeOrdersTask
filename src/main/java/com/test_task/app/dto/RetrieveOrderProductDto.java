package com.test_task.app.dto;

import com.test_task.app.persistence.entity.Product;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Value;
import lombok.experimental.FieldDefaults;

/**
 * {@code RetrieveOrderProductDto} class is used to display information about products that was added to order.
 *
 * @see RetrieveOrderDto
 */
@Value
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RetrieveOrderProductDto {
  Product product;
  Integer units;
}
