package com.test_task.app.rest.controller;

import com.test_task.app.AppApplication;
import com.test_task.app.dto.ProductDto;
import com.test_task.app.dto.RetrieveOrderDto;
import com.test_task.app.dto.SendOrderDto;
import org.junit.Test;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import static com.common.TestConstants.Attributes.Order.ORDER_ID;
import static com.common.TestConstants.Dto.Order.SEND_ORDER_DTO;
import static com.common.TestConstants.Dto.Product.PRODUCT_DTO;
import static com.common.TestConstants.Path.*;
import static com.common.TestConstants.SecureUsers.CLIENT;
import static com.common.TestConstants.SecureUsers.MANAGER;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = AppApplication.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OrderControllerTest {

  @Autowired
  private TestRestTemplate restTemplate;

  @Test
  @Order(1)
  public void clientAddOrderShouldReturnStatusCreated() {
    final var productEntity = new HttpEntity<>(PRODUCT_DTO);
    final var orderEntity = new HttpEntity<>(SEND_ORDER_DTO);

    restTemplate
            .withBasicAuth(MANAGER.getName(), MANAGER.getPassword())
            .exchange(ADD_PRODUCT, HttpMethod.POST, productEntity, ProductDto.class);


    final var response = restTemplate
            .withBasicAuth(CLIENT.getName(), CLIENT.getPassword())
            .exchange(ADD_ORDER, HttpMethod.POST, orderEntity, SendOrderDto.class);

    assertThat(response.getStatusCode(), equalTo(HttpStatus.CREATED));
  }

  @Test
  public void managerAddOrderShouldReturnStatusForbidden() {
    final var productEntity = new HttpEntity<>(PRODUCT_DTO);
    final var orderEntity = new HttpEntity<>(SEND_ORDER_DTO);

    restTemplate
            .withBasicAuth(MANAGER.getName(), MANAGER.getPassword())
            .exchange(ADD_PRODUCT, HttpMethod.POST, productEntity, ProductDto.class);


    final var response = restTemplate
            .withBasicAuth(MANAGER.getName(), MANAGER.getPassword())
            .exchange(ADD_ORDER, HttpMethod.POST, orderEntity, SendOrderDto.class);

    assertThat(response.getStatusCode(), equalTo(HttpStatus.FORBIDDEN));
  }

  @Test
  @Order(2)
  public void clientPayForOrderShouldReturnStatusOk() {
    final var response = restTemplate
            .withBasicAuth(CLIENT.getName(), CLIENT.getPassword())
            .exchange(PAY_FOR_ORDER, HttpMethod.PUT, null, RetrieveOrderDto.class, ORDER_ID);

    assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
  }

  @Test
  public void managerPayForOrderShouldReturnStatusForbidden() {
    final var response = restTemplate
            .withBasicAuth(MANAGER.getName(), MANAGER.getPassword())
            .exchange(PAY_FOR_ORDER, HttpMethod.PUT, null, RetrieveOrderDto.class, ORDER_ID);

    assertThat(response.getStatusCode(), equalTo(HttpStatus.FORBIDDEN));
  }
}