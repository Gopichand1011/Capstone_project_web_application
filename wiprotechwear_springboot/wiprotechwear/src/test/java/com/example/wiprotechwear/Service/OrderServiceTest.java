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

import com.example.wiprotechwear.Model.Order;
import com.example.wiprotechwear.Repository.OrderRepository;

public class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderService orderService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testPlaceOrder() {
        Order order = new Order();
        order.setStatus("Pending");

        Order savedOrder = new Order();
        savedOrder.setStatus("Placed");

        when(orderRepository.save(order)).thenReturn(savedOrder);

        Order result = orderService.placeOrder(order);

        assertEquals("Placed", result.getStatus());
        verify(orderRepository, times(1)).save(order);
    }

    @Test
    public void testGetOrders() {
        Long userId = 1L;
        List<Order> orders = new ArrayList<>();
        orders.add(new Order());

        when(orderRepository.findByUserId(userId)).thenReturn(orders);

        List<Order> result = orderService.getOrders(userId);

        assertEquals(orders.size(), result.size());
        verify(orderRepository, times(1)).findByUserId(userId);
    }
}
