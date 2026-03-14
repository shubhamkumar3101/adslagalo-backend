package com.adslagalo.controller;

import com.adslagalo.dto.OrderResponseDTO;
import com.adslagalo.entity.Order;
import com.adslagalo.service.OrderService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/orders")
@PreAuthorize("hasRole('ADMIN')")
public class AdminOrderController {

    private final OrderService orderService;

    public AdminOrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    // Get all orders
    @GetMapping
    public List<OrderResponseDTO> getAllOrders() {
        return orderService.getAllOrders();
    }

    // Approve order
    @PutMapping("/{orderId}/approve")
    public Order approveOrder(@PathVariable Long orderId) {
        return orderService.approveOrder(orderId);
    }

    // Reject order
    @PutMapping("/{orderId}/reject")
    public Order rejectOrder(@PathVariable Long orderId) {
        return orderService.rejectOrder(orderId);
    }

    // Complete order
    @PutMapping("/{orderId}/complete")
    public Order completeOrder(@PathVariable Long orderId) {
        return orderService.completeOrder(orderId);
    }
}
