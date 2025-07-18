package com.shruti.ordertracking.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    @GetMapping("/")
    public ResponseEntity<String> getOrderDetails() {
        // Logic to fetch order details by orderId
        return ResponseEntity.ok("Order details for order ID: ");
    }

    @GetMapping("/status")
    public ResponseEntity<String> getOrderStatus(@RequestParam String orderId) {
        // Logic to fetch order status by orderId
        return ResponseEntity.status(HttpStatus.OK).body("Order status for order ID " + orderId);
    }

    @PostMapping("/")
    public ResponseEntity<String> createOrder(@RequestParam String productName, @RequestParam int quantity) {
        // Logic to create a new order
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Order created for product: " + productName + " with quantity: " + quantity);
    }
}
