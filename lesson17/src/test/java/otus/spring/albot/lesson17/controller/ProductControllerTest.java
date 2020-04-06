package otus.spring.albot.lesson17.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import otus.spring.albot.lesson17.dto.ProductDto;
import otus.spring.albot.lesson17.entity.Product;
import otus.spring.albot.lesson17.service.ProductService;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
class ProductControllerTest {
    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private ProductService productService;

    @Test
    void getProducts() throws Exception {
        List<ProductDto> dtos = Collections.singletonList(
                ProductDto.fromDao(new Product(1L, "Test", "Test", null, null)));
        given(productService.getAllProducts()).willReturn(dtos);
        MvcResult mvcResult = this.mvc.perform(get("/products")).andExpect(status().isOk()).andReturn();
        String response = mvcResult.getResponse().getContentAsString();
        assertThat(objectMapper.writeValueAsString(dtos)).isEqualTo(response);
    }

    @Test
    void product() throws Exception {
        ProductDto dto = ProductDto.fromDao(new Product(1L, "Test", "Test", null, null));
        given(productService.getProductById(1L)).willReturn(dto);
        MvcResult mvcResult = this.mvc.perform(get("/products/{id}", 1L)).andExpect(status().isOk()).andReturn();
        String response = mvcResult.getResponse().getContentAsString();
        assertThat(objectMapper.writeValueAsString(dto)).isEqualTo(response);
    }

    @Test
    void updateProduct() throws Exception {
        ProductDto dto1 = ProductDto.fromDao(new Product(1L, "Test1", "Test1", null, null));
        ProductDto dto2 = ProductDto.fromDao(new Product(1L, "Test2", "Test2", null, null));
        given(productService.updateProduct(dto1)).willReturn(dto2);
        MvcResult mvcResult = this.mvc.perform(put("/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto1)))
                .andExpect(status().isOk()).andReturn();
        String response = mvcResult.getResponse().getContentAsString();
        assertThat(objectMapper.writeValueAsString(dto2)).isEqualTo(response);
    }

    @Test
    void removeProduct() throws Exception {
        this.mvc.perform(delete("/products").param("id", "1")).andExpect(status().isOk());
    }
}
