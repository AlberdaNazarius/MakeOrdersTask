package com.test_task.app.exception;

import jakarta.annotation.Nonnull;

public class OrderNotFoundException extends RuntimeException {
  public OrderNotFoundException(@Nonnull final String message) {
    super(message);
  }
}
