package com.klagan.challenge.application.services;

import com.klagan.challenge.domain.models.Price;
import com.klagan.challenge.domain.ports.input.PriceUseCases;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class PriceService implements PriceUseCases {
  private final PriceUseCases priceUseCases;

  public PriceService(PriceUseCases priceUseCases) {
    this.priceUseCases = priceUseCases;
  }

  @Override
  public Price findByBrandIdAndProductIdAndStartDate(long id, Long productId, LocalDateTime applicationDate) {
    return priceUseCases.findByBrandIdAndProductIdAndStartDate(id, productId, applicationDate);
  }
}
