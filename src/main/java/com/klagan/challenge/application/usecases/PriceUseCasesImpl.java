package com.klagan.challenge.application.usecases;

import com.klagan.challenge.domain.models.Price;
import com.klagan.challenge.domain.ports.input.PriceUseCases;
import com.klagan.challenge.domain.ports.output.PriceRepositoryPort;
import com.klagan.challenge.infrastructure.entities.PriceEntity;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;
import java.util.Optional;

public class PriceUseCasesImpl implements PriceUseCases {

  public static final String PRICE_NOT_FOUND_EXCEPTION_MSG = "Price not found";
  private final PriceRepositoryPort priceRepositoryPort;
  private final ModelMapper mapper;
  public PriceUseCasesImpl(PriceRepositoryPort priceRepositoryPort, ModelMapper mapper) {
    this.priceRepositoryPort = priceRepositoryPort;
    this.mapper = mapper;
  }

  public Price findByBrandIdAndProductIdAndStartDate(Long brandId, Long productId, LocalDateTime applicationDate) {
    return priceRepositoryPort.findByBrandIdAndProductIdAndStartDate(brandId, productId, applicationDate)
            .map(this::convertPriceEntityToPriceModel)
            .orElseThrow(() -> new EntityNotFoundException(PRICE_NOT_FOUND_EXCEPTION_MSG));
  }
  private Price convertPriceEntityToPriceModel(PriceEntity priceEntity){
    return this.mapper.map(priceEntity, Price.class);
  }

}
