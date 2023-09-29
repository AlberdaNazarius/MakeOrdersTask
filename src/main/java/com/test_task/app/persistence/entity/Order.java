package com.test_task.app.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

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

  @Column(name = "language")
  String language;

  // TODO make to not creating new instances of this one when do post request
  @ManyToMany(cascade = CascadeType.ALL)
  @JoinTable(name = "orders_products",
          joinColumns = @JoinColumn(name = "order_id"),
          inverseJoinColumns = @JoinColumn(name = "product_id"))
  @JsonIgnoreProperties("products")
  Set<Product> products;

  @Column(name = "is_paid")
  Boolean isPaid;
}
