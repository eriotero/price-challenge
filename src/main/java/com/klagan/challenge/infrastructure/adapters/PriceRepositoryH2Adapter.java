package com.klagan.challenge.infrastructure.adapters;

import com.klagan.challenge.domain.ports.output.PriceRepositoryPort;
import com.klagan.challenge.infrastructure.entities.PriceEntity;
import com.klagan.challenge.infrastructure.repositories.PriceRepository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

public class PriceRepositoryH2Adapter implements PriceRepositoryPort {
  private final PriceRepository priceRepository;

  public PriceRepositoryH2Adapter(PriceRepository priceRepository) {
    this.priceRepository = priceRepository;
  }

  @Override
  public Optional<PriceEntity> findByBrandIdAndProductIdAndStartDate(Long brandId, Long productId, LocalDateTime applicationDate) {
    return priceRepository.findByBrandIdAndProductIdAndStartDate(brandId, productId, applicationDate);
  }
}
