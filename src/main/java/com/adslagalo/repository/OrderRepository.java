package com.adslagalo.repository;

import com.adslagalo.entity.Order;
import com.adslagalo.entity.OrderStatus;
import com.adslagalo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    // Get all orders placed by a specific user
    List<Order> findByUser(User user);

    // Get orders by status (for admin filtering)
    List<Order> findByStatus(OrderStatus status);

    // Get orders by user and status
    List<Order> findByUserAndStatus(User user, OrderStatus status);

    List<Order> findAllByOrderByCreatedAtDesc();
}
