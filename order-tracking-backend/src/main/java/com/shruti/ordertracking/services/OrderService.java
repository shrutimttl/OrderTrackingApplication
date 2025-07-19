package com.shruti.ordertracking.services;

import java.util.List;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shruti.ordertracking.entities.Order;
import com.shruti.ordertracking.repositories.OrderRepository;

@Service
public class OrderService {
    @Autowired
    public OrderRepository orderRepository;

    public Order findByOrderId(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }
}