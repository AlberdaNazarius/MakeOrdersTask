package com.test_task.app.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Value;
import lombok.experimental.FieldDefaults;

import java.time.ZonedDateTime;
import java.util.Set;

/**
 * {@code RetrieveOrderDto} class is used to send information from server to client.
 * <p>
 *   It has {@code RetrieveOrderProductDto} set which will display units and products included in this order.
 * </p>
 *
 * @see SendOrderDto
 * @see RetrieveOrderProductDto
 */
@Value
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RetrieveOrderDto {
  String whoCreated;
  ZonedDateTime creationDate;
  Set<RetrieveOrderProductDto> products;
  boolean paid;
}
