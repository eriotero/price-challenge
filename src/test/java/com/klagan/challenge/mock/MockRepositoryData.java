package com.klagan.challenge.mock;

import com.klagan.challenge.infrastructure.entities.PriceEntity;

import java.time.LocalDateTime;

public class MockRepositoryData {

  public static PriceEntity getPriceEntityWithBrandIdProductIdAndStartAndEndDate(
          Long brandId,
          Long productId,
          LocalDateTime startDate,
          LocalDateTime endDate
  ) {
    return new PriceEntity(
            1L,
             brandId,
            startDate,
            endDate,
            1,
            productId,
            0,
            350,
            "EUR"
    );
  }
}
