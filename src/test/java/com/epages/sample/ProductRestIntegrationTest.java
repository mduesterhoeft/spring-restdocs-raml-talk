package com.epages.sample;

import static lombok.AccessLevel.PRIVATE;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.linkWithRel;
import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.links;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.subsectionWithPath;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;
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

@AutoConfigureMockMvc
@AutoConfigureRestDocs
@SpringBootTest
@FieldDefaults(level = PRIVATE)
@RunWith(SpringRunner.class)
public class ProductRestIntegrationTest extends BaseIntegrationTest {

    @Test
    @SneakyThrows
    public void should_get_products() {
        givenProduct();
        givenProduct("Fancy Shirt", "15.10");
        givenProduct("Fancy Shoes", "75.95");

        whenProductsAreRetrieved();

        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("_embedded.products", hasSize(2)))
                .andDo(document("products-get", responseFields(
                        subsectionWithPath("_embedded.products[].name").description("The name of the product."),
                        fieldWithPath("_embedded.products[].price").description("The price of the product."),
                        subsectionWithPath("_embedded.products[]._links").description("The product links."),
                        fieldWithPath("page.size").description("The size of one page."),
                        fieldWithPath("page.totalElements").description("The total number of elements found."),
                        fieldWithPath("page.totalPages").description("The total number of pages."),
                        fieldWithPath("page.number").description("The current page number."),
                        fieldWithPath("page").description("Paging information"),
                        subsectionWithPath("_links").description("Links section")),
                        links(
                                linkWithRel("first").description("Link to the first page"),
                                linkWithRel("next").description("Link to the next page"),
                                linkWithRel("last").description("Link to the next page"),
                                linkWithRel("self").ignored(),
                                linkWithRel("profile").ignored()
                        ),
                        requestParameters(
                                parameterWithName("page").description("The page to be requested."),
                                parameterWithName("size").description("Parameter determining the size of the requested page."),
                                parameterWithName("sort").description("Information about sorting items.")
                        )))
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
                .andDo(document("product-get", responseFields(
                        fieldWithPath("name").description("The name of the product."),
                        fieldWithPath("price").description("The price of the product."),
                        subsectionWithPath("_links").description("Links section")
                )))
        ;
    }

    @Test
    @SneakyThrows
    public void should_create_product() {
        givenProductPayload();

        whenProductIsCreated();

        resultActions
                .andExpect(status().isCreated())
                .andDo(document("products-create", requestFields(
                        fieldWithPath("name").description("The name of the product."),
                        fieldWithPath("price").description("The price of the product.")
                )))
        ;
    }

    @SneakyThrows
    private void whenProductIsRetrieved() {
        resultActions = mockMvc.perform(get("/products/{id}", productId));
    }

    @SneakyThrows
    private void whenProductsAreRetrieved() {
        resultActions = mockMvc.perform(get("/products")
                .param("page", "0")
                .param("size", "2")
                .param("sort", "name asc"))
                .andDo(print());
    }
}
