package com.epages.sample;

import static lombok.AccessLevel.PRIVATE;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.data.rest.webmvc.RestMediaTypes.HAL_JSON;
import static org.springframework.data.rest.webmvc.RestMediaTypes.TEXT_URI_LIST;
import static org.springframework.http.HttpHeaders.LOCATION;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;

@RunWith(SpringRunner.class)
@SpringBootTest
@FieldDefaults(level = PRIVATE)
@AutoConfigureMockMvc
@AutoConfigureRestDocs
public class CartIntegrationTest extends BaseIntegrationTest {

    String cartId;

    @Test
    @SneakyThrows
    public void should_create_cart() {

        whenCartIsCreated();

        resultActions
                .andExpect(status().isCreated())
        ;
    }

    @Test
    @SneakyThrows
    public void should_add_product_to_cart() {
        givenCart();
        givenProduct();

        whenProductIsAddedToCart();

        resultActions
                .andExpect(status().isNoContent())
        ;
    }

    @Test
    @SneakyThrows
    public void should_get_cart() {
        givenCartWithProduct();

        whenCartIsRetrieved();

        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("products", hasSize(1)))
                .andExpect(jsonPath("products[0].quantity", is(1)))
                .andExpect(jsonPath("products[0].product.name", notNullValue()))
                .andExpect(jsonPath("total", notNullValue()))
        ;
    }

    @Test
    @SneakyThrows
    public void should_order_cart() {
        givenCartWithProduct();

        whenCartIsOrdered();

        resultActions
                .andExpect(status().isNoContent())
        ;
    }

    @SneakyThrows
    private void whenProductIsAddedToCart() {
        resultActions = mockMvc.perform(post("/carts/{id}/products", cartId)
                .contentType(TEXT_URI_LIST)
                .content(entityLinks.linkForSingleResource(Product.class, productId).toUri().toString()));
    }

    @SneakyThrows
    private void whenCartIsCreated() {
        resultActions = mockMvc.perform(post("/carts"));

        String location = resultActions.andReturn().getResponse().getHeader(LOCATION);
        cartId = location.substring(location.lastIndexOf("/") + 1);
    }

    @SneakyThrows
    private void whenCartIsRetrieved() {
        resultActions = mockMvc.perform(get("/carts/{id}", cartId)
                .accept(HAL_JSON))
                .andDo(print());
    }

    @SneakyThrows
    private void whenCartIsOrdered() {
        resultActions = mockMvc.perform(post("/carts/{id}/order", cartId));
    }


    @SneakyThrows
    private void givenCart() {
        whenCartIsCreated();
        resultActions.andExpect(status().isCreated());
    }

    @SneakyThrows
    private void givenCartWithProduct() {
        givenCart();
        givenProduct();
        whenProductIsAddedToCart();

        resultActions.andExpect(status().isNoContent());
    }
}
