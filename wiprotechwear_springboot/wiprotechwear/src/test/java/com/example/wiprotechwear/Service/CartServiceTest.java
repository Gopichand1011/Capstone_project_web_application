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

import com.example.wiprotechwear.Model.CartItem;
import com.example.wiprotechwear.Repository.CartItemRepository;

public class CartServiceTest {

    @Mock
    private CartItemRepository cartItemRepository;

    @InjectMocks
    private CartService cartService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetCartItems() {
        Long userId = 1L;
        List<CartItem> cartItems = new ArrayList<>();
        cartItems.add(new CartItem());

        when(cartItemRepository.findByUserId(userId)).thenReturn(cartItems);

        List<CartItem> result = cartService.getCartItems(userId);

        assertEquals(1, result.size());
        verify(cartItemRepository, times(1)).findByUserId(userId);
    }

    @Test
    public void testAddToCart() {
        CartItem cartItem = new CartItem();
        
        when(cartItemRepository.save(cartItem)).thenReturn(cartItem);

        CartItem result = cartService.addToCart(cartItem);

        assertEquals(cartItem, result);
        verify(cartItemRepository, times(1)).save(cartItem);
    }

    @Test
    public void testRemoveFromCart() {
        Long userId = 1L;
        Long productId = 1L;

        cartService.removeFromCart(userId, productId);

        verify(cartItemRepository, times(1)).deleteByUserIdAndProductId(userId, productId);
    }
}

