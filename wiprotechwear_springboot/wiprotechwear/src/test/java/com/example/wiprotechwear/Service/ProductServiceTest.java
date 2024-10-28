package com.example.wiprotechwear.Service;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

import com.example.wiprotechwear.Model.Product;
import com.example.wiprotechwear.Repository.ProductRepository;

public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllProducts() {
        List<Product> products = new ArrayList<>();
        products.add(new Product());

        when(productRepository.findAll()).thenReturn(products);

        List<Product> result = productService.getAllProducts();

        assertEquals(1, result.size());
        verify(productRepository, times(1)).findAll();
    }

    @Test
    public void testGetProductsByCategory() {
        String category = "Electronics";
        List<Product> products = new ArrayList<>();
        products.add(new Product());

        when(productRepository.findByCategory(category)).thenReturn(products);

        List<Product> result = productService.getProductsByCategory(category);

        assertEquals(1, result.size());
        verify(productRepository, times(1)).findByCategory(category);
    }
}

