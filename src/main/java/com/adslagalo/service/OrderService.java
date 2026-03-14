package com.adslagalo.service;

import com.adslagalo.dto.OrderResponseDTO;
import com.adslagalo.entity.*;
import com.adslagalo.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final SocialServiceRepository socialServiceRepository;

    public OrderService(OrderRepository orderRepository,
                        UserRepository userRepository,
                        SocialServiceRepository socialServiceRepository) {

        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.socialServiceRepository = socialServiceRepository;
    }

    public List<OrderResponseDTO> getAllOrders() {

        return orderRepository.findAllByOrderByCreatedAtDesc()
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    // Create Order
    public Order createOrder(Long userId,
                             Long serviceId,
                             Integer quantity,
                             String targetUrl,
                             String screenshotUrl) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        SocialService service = socialServiceRepository.findById(serviceId)
                .orElseThrow(() -> new IllegalArgumentException("Service not found"));

        Order order = Order.builder()
                .user(user)
                .platform(service.getPlatform())
                .service(service)
                .quantity(quantity)
                .targetUrl(targetUrl)
                .priceAtPurchase(service.getPrice())
                .paymentScreenshotUrl(screenshotUrl)
                .status(OrderStatus.PENDING)
                .build();

        return orderRepository.save(order);
    }

    // Get Orders of a User
    public List<Order> getOrdersByUser(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        return orderRepository.findByUser(user);
    }

    // Admin Approve Order
    public Order approveOrder(Long orderId) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));

        order.setStatus(OrderStatus.PROCESSING);

        return orderRepository.save(order);
    }

    // Admin Reject Order
    public Order rejectOrder(Long orderId) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));

        order.setStatus(OrderStatus.REJECTED);

        return orderRepository.save(order);
    }
    public Order completeOrder(Long orderId) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));

        order.setStatus(OrderStatus.COMPLETED);

        return orderRepository.save(order);
    }

    private OrderResponseDTO mapToDTO(Order order) {

        return OrderResponseDTO.builder()
                .orderId(order.getId())
                .userName(order.getUser().getName())
                .platform(order.getPlatform().getName())
                .service(order.getService().getName())
                .quantity(order.getQuantity())
                .price(order.getPriceAtPurchase())
                .status(order.getStatus().name())
                .createdAt(order.getCreatedAt())
                .build();
    }

}
