package com.klagan.challenge.infraestructure.controller;

import com.klagan.challenge.application.services.PriceService;
import com.klagan.challenge.domain.models.Price;
import com.klagan.challenge.domain.ports.input.PriceUseCases;
import com.klagan.challenge.domain.ports.output.PriceRepositoryPort;
import com.klagan.challenge.infrastructure.adapters.PriceRepositoryH2Adapter;
import com.klagan.challenge.infrastructure.entities.PriceEntity;
import com.klagan.challenge.infrastructure.repositories.PriceRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.time.LocalDateTime;
import java.util.Optional;

import static com.klagan.challenge.mock.MockModelData.getPriceWithBrandIdProductIdAndStartAndEndDate;
import static com.klagan.challenge.mock.MockRepositoryData.getPriceEntityWithBrandIdProductIdAndStartAndEndDate;
import static com.klagan.challenge.utils.TestUtil.getLocalDateTime;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class PriceControllerTest {

  @Mock
  private PriceRepositoryPort priceRepositoryPort;
  @Mock
  private PriceUseCases priceUseCases;
  @InjectMocks
  private PriceService priceService;

  @Autowired
  private MockMvc mockMvc;

  @ParameterizedTest
  @CsvSource({
          "1, 35455, 2020-06-14T12:00",
          "1, 35455, 2020-06-14T04:00",
          "1, 35455, 2020-06-14T09:00",
          "1, 35455, 2020-06-14T10:00",
          "1, 35455, 2020-06-14T09:00"
  })
  public void itShouldReturnPriceWhenPriceIsFoundBasedOnSearchCriteria(String brand, String product, String applicationDate) throws Exception {

    // Given
    Long brandId = Long.parseLong(brand);
    Long productId = Long.parseLong(product);
    LocalDateTime startDate = getLocalDateTime(applicationDate);
    LocalDateTime endDate = getLocalDateTime("2020-12-31T11:59:59");
    PriceEntity priceEntity = getPriceEntityWithBrandIdProductIdAndStartAndEndDate(brandId, productId, startDate, endDate);
    Price mockPrice = getPriceWithBrandIdProductIdAndStartAndEndDate(brandId, productId, startDate, endDate);
    Price expectedPrice;

    // When;
    when(priceRepositoryPort.findByBrandIdAndProductIdAndStartDate(brandId, productId, startDate)).thenReturn(Optional.of(priceEntity));
    when(priceUseCases.findByBrandIdAndProductIdAndStartDate(brandId, productId, startDate)).thenReturn(mockPrice);
    when(priceService.findByBrandIdAndProductIdAndStartDate(brandId, productId, startDate)).thenReturn(mockPrice);

    // TODO this tests is not done yet, there is an error I've been debugging related to the priceRepository not returning the specified mocked entity

    // Then
   mockMvc.perform(get("/api/v1/prices")
                    .param("brand-id",String.valueOf(brandId))
                    .param("product-id",String.valueOf(productId))
                    .param("application",startDate.toString()))
            .andExpect(status().isOk())
            .andReturn();

  }

}
