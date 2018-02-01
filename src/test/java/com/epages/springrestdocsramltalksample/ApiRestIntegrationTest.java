package com.epages.springrestdocsramltalksample;

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

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.hateoas.EntityLinks;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;

@AutoConfigureMockMvc
@AutoConfigureRestDocs
@SpringBootTest
@FieldDefaults(level = PRIVATE)
@RunWith(SpringRunner.class)
public class ApiRestIntegrationTest {

    @Autowired MockMvc mockMvc;

    @Autowired ProductRepository productRepository;

    @Autowired CartRepository cartsRepository;

    @Autowired EntityLinks entityLinks;

    ResultActions resultActions;

    String json;

    String productId;
    String cartId;

    @Before
    public void setUp() {
        cartsRepository.deleteAll();
        productRepository.deleteAll();
    }

    @Test
    @SneakyThrows
    public void should_create_cart() {

        whenCartIsCreated();

        resultActions.andExpect(status().isCreated());
    }

    @Test
    @SneakyThrows
    public void should_add_product_to_cart() {
        givenCart();
        givenProduct();

        whenProductIsAddedToCart();

        resultActions.andExpect(status().isNoContent());
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
    public void should_get_products() {
        givenProduct();

        whenProductsAreRetrieved();

        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("_embedded.products", hasSize(1)))
                .andExpect(jsonPath("_embedded.products[0].name", notNullValue()))
        ;
    }

    @Test
    @SneakyThrows
    public void should_get_product() {
        givenProduct();

        whenProductIsRetrieved();

        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("name", notNullValue()))
                .andExpect(jsonPath("price", notNullValue()))
        ;
    }

    @Test
    @SneakyThrows
    public void should_create_product() {
        givenProductPayload();

        whenProductIsCreated();

        resultActions
                .andExpect(status().isCreated())
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
        cartId = location.substring(location.lastIndexOf("/"));
    }

    @SneakyThrows
    private void whenCartIsRetrieved() {
        resultActions = mockMvc.perform(get("/carts/{id}", cartId)
                .accept(HAL_JSON))
                .andDo(print());
    }

    @SneakyThrows
    private void whenProductIsCreated() {
        resultActions = mockMvc.perform(post("/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json));

        String location = resultActions.andReturn().getResponse().getHeader(LOCATION);
        productId = location.substring(location.lastIndexOf("/"));
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
    private void givenProductPayload() {
        json = "{\n" +
                "    \"name\": \"Fancy Product\",\n" +
                "    \"price\": 12.0\n" +
                "}";
    }

    @SneakyThrows
    private void whenProductIsRetrieved() {
        resultActions = mockMvc.perform(get("/products/{id}", productId));
    }

    @SneakyThrows
    private void whenProductsAreRetrieved() {
        resultActions = mockMvc.perform(get("/products"));
    }

    private void givenProduct() {
        givenProductPayload();
        whenProductIsCreated();
    }
}
