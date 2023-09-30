package com.test_task.app.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Value;
import lombok.experimental.FieldDefaults;

import java.time.ZonedDateTime;
import java.util.Set;

@Value
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderDto {
  String whoCreated;
  ZonedDateTime creationDate;
  Set<ProductInOrder> products;
  boolean paid;
}
