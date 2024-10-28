package com.example.wiprotechwear.Controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.wiprotechwear.Model.Order;
import com.example.wiprotechwear.Service.OrderService;

@WebMvcTest(OrderController.class)
public class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderService orderService;

    @Test
    public void testPlaceOrder() throws Exception {
        Order order = new Order();
        order.setStatus("Pending");

        // Perform POST request to place an order
        mockMvc.perform(post("/api/orders/place")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"status\":\"Pending\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("Order placed successfully"));

        // Verify the placeOrder method is called in the service
        verify(orderService, times(1)).placeOrder(any(Order.class));
    }

    @Test
    public void testGetOrderStatus() throws Exception {
        Long userId = 1L;
        List<Order> orders = new ArrayList<>();
        orders.add(new Order());

        when(orderService.getOrders(userId)).thenReturn(orders);

        // Perform GET request to track order status
        mockMvc.perform(get("/api/orders/track/{userId}", userId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1));

        // Verify the getOrders method is called in the service
        verify(orderService, times(1)).getOrders(userId);
    }
}
