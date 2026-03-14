package com.adslagalo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // User who placed the order
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // Platform (Instagram, YouTube, etc.)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "platform_id", nullable = false)
    private Platform platform;

    // Service (Followers, Likes, etc.)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "service_id", nullable = false)
    private SocialService service;

    // Quantity purchased
    @Column(nullable = false)
    private Integer quantity;

    // Target profile / post link
    @Column(nullable = false, length = 500)
    private String targetUrl;

    // Price at the time of purchase
    @Column(nullable = false)
    private BigDecimal priceAtPurchase;

    // Payment screenshot path or URL
    @Column(length = 500)
    private String paymentScreenshotUrl;

    // Order lifecycle status
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderStatus status;

    // Timestamp
    @Column(nullable = false)
    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }
}
