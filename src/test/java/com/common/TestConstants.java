package com.common;

import com.test_task.app.dto.ProductDto;
import com.test_task.app.dto.SendOrderDto;
import com.test_task.app.dto.SendOrderProductDto;
import lombok.experimental.UtilityClass;

import java.util.HashSet;
import java.util.Set;

import static com.common.TestConstants.Attributes.Order.ORDER_IS_PAID;
import static com.common.TestConstants.Attributes.Order.ORDER_WHO_CREATE;
import static com.common.TestConstants.Attributes.OrderProduct.UNITS;
import static com.common.TestConstants.Attributes.Product.*;
import static com.common.TestConstants.Dto.OrderProduct.SEND_ORDER_PRODUCT_DTO;

@UtilityClass
public class TestConstants {
  @UtilityClass
  public static class Path {
    public static final String GET_ALL_PRODUCTS = "/api/product/products";
    public static final String ADD_PRODUCT = "/api/product";
    public static final String GET_ALL_ORDERS = "/api/order/orders";
    public static final String ADD_ORDER = "/api/order";
    public static final String PAY_FOR_ORDER = "/api/order/{orderId}/payed";
  }

  @UtilityClass
  public static class Attributes {
    @UtilityClass
    public static class Product {
      public static final Long PRODUCT_ID = 1L;
      public static final String PRODUCT_NAME = "Apple";
      public static final String PRODUCT_PRICE = "51";
      public static final Long PRODUCT_QUANTITY = 12L;
    }
    @UtilityClass
    public static class Order {
      public static final Long ORDER_ID  = 1L;
      public static final String ORDER_WHO_CREATE = "Jon";
      public static final boolean ORDER_IS_PAID = false;
    }
    @UtilityClass
    public static class OrderProduct {
      public static final Integer UNITS = 12;
    }
  }

  @UtilityClass
  public static class Dto {
    @UtilityClass
    public static class Product {
      public final static ProductDto PRODUCT_DTO = ProductDto.builder()
              .name(PRODUCT_NAME)
              .price(PRODUCT_PRICE)
              .quantity(PRODUCT_QUANTITY)
              .build();
    }
    @UtilityClass
    public static class Order {
      public final static SendOrderDto SEND_ORDER_DTO = SendOrderDto.builder()
              .whoCreated(ORDER_WHO_CREATE)
              .paid(ORDER_IS_PAID)
              .products(OrderProduct.SEND_ORDER_PRODUCT_DTOS)
              .build();
    }
    @UtilityClass
    public static class OrderProduct {
      public final static SendOrderProductDto SEND_ORDER_PRODUCT_DTO = SendOrderProductDto.builder()
              .productId(PRODUCT_ID)
              .units(UNITS)
              .build();
      public final static Set<SendOrderProductDto> SEND_ORDER_PRODUCT_DTOS = new HashSet<>(){{
        add(SEND_ORDER_PRODUCT_DTO);
      }};
    }
  }

  @UtilityClass
  public static class SecureUsers {
    public static final SecuredUser MANAGER = SecuredUser.builder()
            .name("manager")
            .password("1234")
            .build();

    public static final SecuredUser CLIENT = SecuredUser.builder()
            .name("client")
            .password("1234")
            .build();
  }
}
