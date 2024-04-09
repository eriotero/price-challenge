package com.klagan.challenge.domain.ports.output;

import com.klagan.challenge.infrastructure.entities.PriceEntity;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

public interface PriceRepositoryPort {
  Optional<PriceEntity> findByBrandIdAndProductIdAndStartDate(Long brandId, Long productId, LocalDateTime applicationDate);
}
