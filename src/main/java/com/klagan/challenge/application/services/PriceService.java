package com.klagan.challenge.application.services;

import com.klagan.challenge.domain.models.Price;
import com.klagan.challenge.domain.ports.input.PriceUseCases;

import java.time.LocalDateTime;

public class PriceService {
  private final PriceUseCases priceUseCases;

  public PriceService(PriceUseCases priceUseCases) {
    this.priceUseCases = priceUseCases;
  }


  public Price findByBrandIdAndProductIdAndStartDate(Long id, Long productId, LocalDateTime applicationDate) {
    return priceUseCases.findByBrandIdAndProductIdAndStartDate(id, productId, applicationDate);
  }
}
