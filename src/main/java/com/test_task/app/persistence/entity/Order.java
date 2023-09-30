package com.test_task.app.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.time.ZonedDateTime;
import java.util.Set;

@Entity(name = "order")
@Table(name = "order_table")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(exclude = "products")
public class Order {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

  @Column(name = "who_created")
  String whoCreated;

  @CreationTimestamp
  @Column(name = "creation_date")
  ZonedDateTime creationDate;

  @OneToMany(mappedBy = "order", cascade = CascadeType.REMOVE)
  @JsonIgnoreProperties("products")
  Set<OrderProduct> products;

  @Column(name = "is_paid")
  boolean paid;
}
