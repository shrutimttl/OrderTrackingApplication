package com.shruti.ordertracking.services;

import java.util.List;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;

import com.shruti.ordertracking.entities.Order;
import com.shruti.ordertracking.repositories.OrderRepository;

public class OrderService {
    @Autowired
    public OrderRepository orderRepository;

    public String findByOrderId(Long orderId) {
        return orderRepository.findById(orderId)
                .map(order -> order.toString())
                .orElse("Order not found");
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }
}