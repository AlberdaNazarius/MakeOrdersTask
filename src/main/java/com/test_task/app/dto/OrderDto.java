package com.test_task.app.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Value;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Value
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderDto {
  String whoCreated;
  String language;
  List<ProductDto> products;
}
