package com.test_task.app.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Value;
import lombok.experimental.FieldDefaults;

import java.time.ZonedDateTime;
import java.util.Set;

/**
 * {@code SendOrderDto} class is used as DTO from client to the server.
 * <p>
 *    It contains {@code SendOrderProductDto} which has id of the product that need to be added.
 * </p>
 *
 * @see SendOrderProductDto
 * @see RetrieveOrderDto
 */
@Value
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SendOrderDto {
  String whoCreated;
  ZonedDateTime creationDate;
  Set<SendOrderProductDto> products;
  boolean paid;
}
