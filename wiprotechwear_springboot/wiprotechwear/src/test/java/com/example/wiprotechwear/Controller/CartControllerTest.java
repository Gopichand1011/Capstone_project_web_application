package com.example.wiprotechwear.Controller;

import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.wiprotechwear.Model.CartItem;
import com.example.wiprotechwear.Service.CartService;

@WebMvcTest(CartController.class)
public class CartControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CartService cartService;

    @Test
    public void testAddToCart() throws Exception {
        CartItem cartItem = new CartItem();

        mockMvc.perform(post("/api/cart/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"productId\":1,\"quantity\":1}"))
                .andExpect(status().isOk())
                .andExpect(content().string("Successfully added to cart"));

        verify(cartService, times(1)).addToCart(any(CartItem.class));
    }

    @Test
    public void testRemoveFromCart() throws Exception {
        Long userId = 1L;
        Long productId = 1L;

        mockMvc.perform(delete("/api/cart/{userId}/product/{productId}", userId, productId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Removed from cart"));

        verify(cartService, times(1)).removeFromCart(userId, productId);
    }
}
