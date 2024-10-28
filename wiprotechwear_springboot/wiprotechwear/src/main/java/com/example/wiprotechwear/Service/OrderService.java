package com.example.wiprotechwear.Service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.wiprotechwear.Model.Order;
import com.example.wiprotechwear.Repository.OrderRepository;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public Order placeOrder(Order order) {
        order.setStatus("Placed");
        return orderRepository.save(order);
    }

    public List<Order> getOrders(Long userId) {
        return orderRepository.findByUserId(userId);
    }
}




