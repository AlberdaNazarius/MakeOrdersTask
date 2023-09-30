package com.test_task.app.configuration.scheduler;

import com.test_task.app.persistence.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class OrderScheduler {

  private final OrderRepository orderRepository;

  private static final int SCHEDULER_FIXED_RATE = 60000;
  private static final int EXPIRATION_TIME = 10;

  /**
   * Deletes expired orders after some given time.
   * <p>
   *   {@code EXPIRATION_TIME} here is configured in minutes and {@code SCHEDULER_FIXED_RATE} in milliseconds
   * </p>
   */
  @Scheduled(fixedRate = SCHEDULER_FIXED_RATE)
  @Transactional
  public void deleteExpiredOrders() {
    final var now = ZonedDateTime.now();
    final var notPaidOrders = orderRepository.findByPaid(false);
    final var expiredOrders = notPaidOrders.stream()
            .filter(order -> Duration.between(order.getCreationDate(), now).toMinutes() >= EXPIRATION_TIME)
            .collect(Collectors.toList());

    if (!expiredOrders.isEmpty()) {
      log.info("Unpaid orders was deleted");
      orderRepository.deleteAll(expiredOrders);
    }
  }
}
