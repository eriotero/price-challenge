package com.klagan.challenge.infrastructure.repositories;

import com.klagan.challenge.infrastructure.entities.PriceEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface PriceRepository extends CrudRepository<PriceEntity, Long> {
  Optional<PriceEntity> findByBrandIdAndProductIdAndStartDate(Long brandId, Long productId, LocalDateTime applicationDate);
}
