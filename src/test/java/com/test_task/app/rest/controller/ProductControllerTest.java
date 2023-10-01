package com.test_task.app.rest.controller;

import com.test_task.app.AppApplication;
import com.test_task.app.dto.ProductDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.List;

import static com.common.TestConstants.Attributes.Product.*;
import static com.common.TestConstants.Dto.Product.PRODUCT_DTO;
import static com.common.TestConstants.Path.ADD_PRODUCT;
import static com.common.TestConstants.Path.GET_ALL_PRODUCTS;
import static com.common.TestConstants.SecureUsers.CLIENT;
import static com.common.TestConstants.SecureUsers.MANAGER;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = AppApplication.class)
public class ProductControllerTest {

  @Autowired
  private TestRestTemplate restTemplate;

  @Test
  public void managerRetrieveAllAvailableProductsAndReturnStatusOk() {
    final var response = restTemplate
            .withBasicAuth(MANAGER.getName(), MANAGER.getPassword())
            .exchange(GET_ALL_PRODUCTS, HttpMethod.GET, null, List.class);

    assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
  }

  @Test
  public void clientRetrieveAllAvailableProductsAndReturnStatusOk() {
    final var response = restTemplate
            .withBasicAuth(CLIENT.getName(), CLIENT.getPassword())
            .exchange(GET_ALL_PRODUCTS, HttpMethod.GET, null, List.class);

    assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
  }

  @Test
  public void managerAddProductShouldReturnStatusCreated() {
    final var entity = new HttpEntity<>(PRODUCT_DTO);
    final var response = restTemplate
            .withBasicAuth(MANAGER.getName(), MANAGER.getPassword())
            .exchange(ADD_PRODUCT, HttpMethod.POST, entity, ProductDto.class);

    final var dtoObjectResponse = response.getBody();

    assertThat(response.getStatusCode(), equalTo(HttpStatus.CREATED));
    assertThat(dtoObjectResponse.getName(), equalTo(PRODUCT_NAME));
    assertThat(dtoObjectResponse.getQuantity(), equalTo(PRODUCT_QUANTITY));
    assertThat(dtoObjectResponse.getPrice(), equalTo(PRODUCT_PRICE));
  }

  @Test
  public void clientAddProductShouldReturnStatusForbidden() {
    final var entity = new HttpEntity<>(PRODUCT_DTO);
    final var response = restTemplate
            .withBasicAuth(CLIENT.getName(), CLIENT.getPassword())
            .exchange(ADD_PRODUCT, HttpMethod.POST, entity, ProductDto.class);

    assertThat(response.getStatusCode(), equalTo(HttpStatus.FORBIDDEN));
  }
}