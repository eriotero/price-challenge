package com.klagan.challenge.application.services;

import com.klagan.challenge.domain.models.Price;
import com.klagan.challenge.domain.ports.input.PriceUseCases;
import com.klagan.challenge.domain.ports.output.PriceRepositoryPort;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Timestamp;
import java.text.ParseException;
import java.time.LocalDateTime;

import static com.klagan.challenge.application.usecases.PriceUseCasesImpl.PRICE_NOT_FOUND_EXCEPTION_MSG;
import static com.klagan.challenge.mock.MockModelData.getPriceWithBrandIdProductIdAndStartAndEndDate;
import static com.klagan.challenge.utils.TestUtil.getLocalDateTime;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PriceServiceTest {
  @Mock
  private PriceUseCases priceUseCases;
  @Mock
  private PriceRepositoryPort priceRepository;
  @InjectMocks
  private PriceService priceService;

  @Test
  public void itShouldReturnPriceWhenPriceIsFound() throws ParseException {
    // Given
    Long brandId = 999L;
    Long productId = 999L;
    LocalDateTime startDate = getLocalDateTime("2019-12-31 11:59:59");
    LocalDateTime endDate = getLocalDateTime("2020-12-31 11:59:59");
    Price mockPrice = getPriceWithBrandIdProductIdAndStartAndEndDate(brandId, productId, startDate, endDate);
    Price expectedPrice;

    // When
    when(priceUseCases.findByBrandIdAndProductIdAndStartDate(brandId, productId, startDate)).thenReturn(mockPrice);

    expectedPrice = priceService.findByBrandIdAndProductIdAndStartDate(brandId, productId, startDate);

    // Then
    assertAll(
            "",
            () -> {
              assertSame(expectedPrice.getId(), mockPrice.getId());
              assertSame(expectedPrice.getBrandId(), mockPrice.getBrandId());
              assertEquals(expectedPrice.getPrice(), mockPrice.getPrice());
              assertEquals(expectedPrice.getPriceList(), mockPrice.getPriceList());
              assertEquals(expectedPrice.getCurrency(), mockPrice.getCurrency());
              assertEquals(expectedPrice.getStartDate(), mockPrice.getStartDate());
              assertEquals(expectedPrice.getEndDate(), mockPrice.getEndDate());
              assertEquals(expectedPrice.getPriority(), mockPrice.getPriority());
              assertEquals(expectedPrice.getProductId(), mockPrice.getProductId());
            }
    );
  }

  @Test
  public void itShouldReturn404WhenPriceIsNotFound() throws ParseException {

    // Given
    Long brandId = 999L;
    Long productId = 999L;
    LocalDateTime startDate = getLocalDateTime("2021-12-31 11:59:59");
    LocalDateTime endDate = getLocalDateTime("2020-12-31 11:59:59");

    // When
    when(priceUseCases.findByBrandIdAndProductIdAndStartDate(brandId, productId, startDate)).thenThrow(
            new EntityNotFoundException(PRICE_NOT_FOUND_EXCEPTION_MSG)
    );

    assertThrows(EntityNotFoundException.class, () -> {
        priceService.findByBrandIdAndProductIdAndStartDate(brandId, productId, startDate);
      }
    );
  }
}
