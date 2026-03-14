package com.adslagalo.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderResponseDTO {

    private Long orderId;
    private String userName;
    private String platform;
    private String service;
    private Integer quantity;
    private BigDecimal price;
    private String status;
    private LocalDateTime createdAt;

}