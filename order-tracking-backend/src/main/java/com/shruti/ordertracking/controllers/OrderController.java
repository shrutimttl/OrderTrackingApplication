package com.shruti.ordertracking.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shruti.ordertracking.entities.Order;
import com.shruti.ordertracking.services.KafkaProducerService;
import com.shruti.ordertracking.services.OrderService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;
    private final KafkaProducerService kafkaProducerService;

    public OrderController(OrderService orderService, KafkaProducerService kafkaProducerService) {
        this.orderService = orderService;
        this.kafkaProducerService = kafkaProducerService;
    }

    @GetMapping("/")
    public ResponseEntity<List<Order>> getOrderDetails() {
        List<Order> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/status/{id}")
    public ResponseEntity<String> getOrderStatus(@PathVariable Long id) {
        // Logic to fetch order status by orderId
        Order order = orderService.findByOrderId(id);
        String status = order.getStatus();
        if (status == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order not found for ID: " + id);
        }
        return ResponseEntity.status(HttpStatus.OK).body("Order status for order ID " + id + ": " + status);
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getOrderDetail(@PathVariable Long id) {
        // Logic to fetch order status by orderId
        Order order = orderService.findByOrderId(id);
        if (order == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order not found for ID: " + id);
        }
        return ResponseEntity.status(HttpStatus.OK).body("Order status for order ID " + id + ": " + order);
    }

    @PostMapping("/")
    public ResponseEntity<String> createOrder(@RequestBody Order order) {
        String productName = order.getProductName();
        int quantity = order.getQuantity();
        if (productName == null || productName.isEmpty() || quantity <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid product name or quantity");
        }
        // Logic to create a new order
        Order newOrder = Order.builder().productName(productName)
                .quantity(quantity)
                .status("Created") // Default status
                .build();
        orderService.createOrder(newOrder);
        // Send to Kafka
        kafkaProducerService.sendOrderEvent(newOrder);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Order created for product: " + productName + " with quantity: " + quantity);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateOrder(@PathVariable Long id, @RequestBody Order updatedOrder) {
        // TODO: process PUT request
        Order order = orderService.findByOrderId(id);
        if (order == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order not found for ID: " + id);
        }
        order.setProductName(updatedOrder.getProductName());
        order.setQuantity(updatedOrder.getQuantity());
        order.setStatus(updatedOrder.getStatus());
        orderService.createOrder(order);
        return ResponseEntity.ok("PUT request processed for order ID: " + id + " with order details: " + order);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> partialUpdateOrder(@PathVariable Long id, @RequestBody Order order) {
        if (orderService.findByOrderId(id) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order not found for ID: " + id);
        }
        Order existingOrder = orderService.findByOrderId(id);
        if (order.getProductName() != null) {
            existingOrder.setProductName(order.getProductName());
        }
        if (order.getQuantity() > 0) {
            existingOrder.setQuantity(order.getQuantity());
        }
        if (order.getStatus() != null) {
            existingOrder.setStatus(order.getStatus());
        }
        orderService.createOrder(existingOrder);
        return ResponseEntity.ok("PATCH request processed for order ID: " + id + " with order details: " + order);
    }
}
