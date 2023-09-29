package com.test_task.app.configuration.scheduler;

import com.test_task.app.persistence.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OrderScheduler {

  private final OrderRepository orderRepository;

  private static final int SCHEDULER_FIXED_RATE = 60000;
  private static final int EXPIRATION_TIME = 10;

  @Scheduled(fixedRate = SCHEDULER_FIXED_RATE)
  @Transactional
  public void deleteExpiredOrders() {
    final var now = ZonedDateTime.now().getMinute();
    final var notPaidOrders = orderRepository.findByIsPaid(false);
    final var expiredOrders = notPaidOrders.stream()
            .filter(x -> now - x.getCreationDate().getMinute() >= EXPIRATION_TIME)
            .collect(Collectors.toList());

    if (!expiredOrders.isEmpty()) {
      orderRepository.deleteAll(expiredOrders);
    }
  }
}
