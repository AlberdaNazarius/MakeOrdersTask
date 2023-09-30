package com.test_task.app.persistence.repository;

import com.test_task.app.persistence.OrderProductId;
import com.test_task.app.persistence.entity.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderProductRepository extends JpaRepository<OrderProduct, OrderProductId> {
}
