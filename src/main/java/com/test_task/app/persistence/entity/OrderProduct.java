package com.test_task.app.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.test_task.app.persistence.OrderProductId;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "order_product")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(exclude = {"order", "product"})
public class OrderProduct {
  @EmbeddedId
  OrderProductId id;

  @ManyToOne
  @MapsId("orderId")
  @JoinColumn(name = "order_id")
  @JsonIgnore
  Order order;

  @ManyToOne
  @MapsId("productId")
  @JoinColumn(name = "product_id")
  Product product;

  Integer units;
}
