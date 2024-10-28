package com.example.wiprotechwear.Controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.wiprotechwear.Model.Product;
import com.example.wiprotechwear.Service.ProductService;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Test
    public void testGetAllProducts() throws Exception {
        List<Product> products = new ArrayList<>();
        products.add(new Product());

        when(productService.getAllProducts()).thenReturn(products);

        mockMvc.perform(get("/api/products/get")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1));

        verify(productService, times(1)).getAllProducts();
    }

    @Test
    public void testGetProductsByCategory() throws Exception {
        String category = "Electronics";
        List<Product> products = new ArrayList<>();
        products.add(new Product());

        when(productService.getProductsByCategory(category)).thenReturn(products);

        mockMvc.perform(get("/api/products/category/{category}", category)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1));

        verify(productService, times(1)).getProductsByCategory(category);
    }
}
