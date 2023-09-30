package com.test_task.app.dto;

import com.test_task.app.persistence.entity.Product;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Value;
import lombok.experimental.FieldDefaults;

@Value
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RetrieveOrderProductDto {
  Product product;
  Integer units;
}
