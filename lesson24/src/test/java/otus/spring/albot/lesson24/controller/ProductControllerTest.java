package otus.spring.albot.lesson24.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import otus.spring.albot.lesson24.entity.Product;
import otus.spring.albot.lesson24.service.NoteService;
import otus.spring.albot.lesson24.service.ProductService;

import java.util.Collections;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
class ProductControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ProductService productService;
    @MockBean
    private NoteService noteService;

    @Test
    @WithMockUser(username = "admin", password = "admin", authorities = {"ROLE_ADMIN"})
    public void productList() throws Exception {
        given(productService.getAllProducts()).willReturn(Collections.emptyList());
        mockMvc.perform(get("/products")).andExpect(status().isOk());
    }

    @Test
    public void productListNotAuthorized() throws Exception {
        given(productService.getAllProducts()).willReturn(Collections.emptyList());
        mockMvc.perform(get("/products")).andExpect(status().is3xxRedirection());
    }

    @Test
    @WithMockUser(username = "admin", password = "admin", authorities = {"ROLE_ADMIN"})
    void product() throws Exception {
        given(productService.getProductById(1L)).willReturn(new Product());
        mockMvc.perform(get("/products/1")).andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "admin", password = "admin", authorities = {"ROLE_ADMIN"})
    void addNote() throws Exception {
        doNothing().when(productService).addComment(1L, "Test");
        mockMvc.perform(post("/products/addNote")
                .param("productId", "1")
                .param("comment", "Test")).andExpect(status().is3xxRedirection());
    }

    @Test
    @WithMockUser(username = "admin", password = "admin", authorities = {"ROLE_ADMIN"})
    void deleteNote() throws Exception {
        doNothing().when(noteService).removeNote(1L);
        mockMvc.perform(post("/products/1")
                .param("noteToDelete", "1")).andExpect(status().is3xxRedirection());
    }

    @Test
    @WithMockUser(username = "admin", password = "admin", authorities = {"ROLE_ADMIN"})
    void editProduct() throws Exception {
        given(productService.getProductById(1L)).willReturn(new Product());
        mockMvc.perform(get("/product/edit").param("id", "1")).andExpect(status().isOk());
    }

    @Test
    void deleteProduct() throws Exception {
        doNothing().when(productService).deleteProduct(1L);
        mockMvc.perform(get("/product/delete").param("id", "1"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    void save() throws Exception {
        Product initialProduct = new Product();
        Product savedProduct = new Product();
        savedProduct.setId(1L);
        given(productService.save(initialProduct)).willReturn(savedProduct);
        mockMvc.perform(post("/product/save").content(initialProduct.toString()))
                .andExpect(status().is3xxRedirection());
    }
}
