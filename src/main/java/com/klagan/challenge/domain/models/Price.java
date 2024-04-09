package com.klagan.challenge.domain.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Price {
    private Long id;
    private Long brandId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private int priceList;
    private Long productId;
    private int priority;
    private double price;
    private String currency;
}