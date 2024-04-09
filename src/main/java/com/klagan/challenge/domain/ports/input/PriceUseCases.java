package com.klagan.challenge.domain.ports.input;

import com.klagan.challenge.domain.models.Price;

import java.time.LocalDateTime;

public interface PriceUseCases {
  Price findByBrandIdAndProductIdAndStartDate(Long brandId, Long productId, LocalDateTime applicationDate);
}
