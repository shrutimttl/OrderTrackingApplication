package com.shruti.ordertracking.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shruti.ordertracking.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
    // Custom query methods can be defined here if needed

}
