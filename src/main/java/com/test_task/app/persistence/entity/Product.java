package com.test_task.app.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Entity
@Table
@Data
@Builder
@ToString(exclude = "orders")
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Product {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

  @Column(name = "name", nullable = false)
  String name;

  @Column(name = "price", nullable = false)
  String price;

  @Column(name = "quantity", nullable = false)
  Long quantity;

  @JsonIgnore
  @OneToMany(mappedBy = "product")
  Set<OrderProduct> orders;
}
