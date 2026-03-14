package com.adslagalo.controller;

import com.adslagalo.entity.Order;
import com.adslagalo.service.OrderService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    // User creates order
    @PostMapping
    public Order createOrder(@RequestParam Long userId,
                             @RequestParam Long serviceId,
                             @RequestParam Integer quantity,
                             @RequestParam String targetUrl,
                             @RequestParam String screenshotUrl) {

        return orderService.createOrder(
                userId,
                serviceId,
                quantity,
                targetUrl,
                screenshotUrl
        );
    }

    // User gets their orders
    @GetMapping("/user/{userId}")
    public List<Order> getUserOrders(@PathVariable Long userId) {

        return orderService.getOrdersByUser(userId);
    }

    // Admin approves order
    @PatchMapping("/admin/approve/{orderId}")
    @PreAuthorize("hasRole('ADMIN')")
    public Order approveOrder(@PathVariable Long orderId) {

        return orderService.approveOrder(orderId);
    }

    // Admin rejects order
    @PatchMapping("/admin/reject/{orderId}")
    @PreAuthorize("hasRole('ADMIN')")
    public Order rejectOrder(@PathVariable Long orderId) {

        return orderService.rejectOrder(orderId);
    }
}
