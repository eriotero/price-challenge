package com.klagan.challenge.domain.ports.input;

import com.klagan.challenge.domain.models.Price;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public interface PriceUseCases {
  Price findByBrandIdAndProductIdAndStartDate(long id, Long productId, LocalDateTime applicationDate);
}
