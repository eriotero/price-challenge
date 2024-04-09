package com.klagan.challenge.mock;

import com.klagan.challenge.domain.models.Price;

import java.time.LocalDateTime;

public class MockModelData {

  public static Price getPriceWithBrandIdProductIdAndStartAndEndDate(
          Long brandId,
          Long productId,
          LocalDateTime startDate,
          LocalDateTime endDate
  ){
    return Price.builder()
            .brandId(brandId)
            .price(350)
            .startDate(startDate)
            .endDate(endDate)
            .productId(productId)
            .currency("EUR")
            .priority(0)
            .priceList(1)
            .build();
  }
}
