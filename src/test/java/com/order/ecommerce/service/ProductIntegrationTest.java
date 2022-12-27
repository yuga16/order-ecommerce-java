package com.order.ecommerce.service;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import io.zonky.test.db.AutoConfigureEmbeddedDatabase;
import javax.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.order.ecommerce.dto.OrderDto;
import com.order.ecommerce.dto.ProductDto;
import com.order.ecommerce.entity.Product;
import com.order.ecommerce.util.OrderUtil;

import static org.mockito.Mockito.when;

@SpringBootTest
@Transactional
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@AutoConfigureMockMvc
@Sql("/product/insert_prerequisite_records.sql")
public class ProductIntegrationTest {
	private static final OrderDto orderDtoRequest = OrderUtil.createTestOrder();

    @Autowired
    private MockMvc mockMvc;
    
    @Mock
    private ProductService productService;

    @Test
    void testGetProduct() throws Exception {
    	when(productService.findProductById("106")).thenReturn(new ProductDto("106","sku","SoftDrink","Coke",5.99));
        mockMvc.perform(
                        MockMvcRequestBuilders.get("/api/v1/products/106")
                ).andExpect(status().is(200))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(
                        content().json("{\n"
                                + "                      \"productId\": \"106\",\n"
                                + "                      \"sku\": \"1006\",\n"
                                + "                      \"title\": \"SoftDrink\",\n"
                                + "                      \"description\": \"Coke\",\n"
                                + "                      \"price\": 5.99\n"
                                + "                    }")
                ).andReturn();
    }
}
