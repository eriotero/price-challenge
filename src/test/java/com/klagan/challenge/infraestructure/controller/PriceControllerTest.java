package com.klagan.challenge.infraestructure.controller;

import com.klagan.challenge.application.services.PriceService;
import com.klagan.challenge.application.usecases.PriceUseCasesImpl;
import com.klagan.challenge.domain.models.Price;
import com.klagan.challenge.domain.ports.output.PriceRepositoryPort;
import com.klagan.challenge.infrastructure.adapters.PriceRepositoryH2Adapter;
import com.klagan.challenge.infrastructure.entities.PriceEntity;
import com.klagan.challenge.infrastructure.repositories.PriceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.MockitoAnnotations;
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
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class PriceControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private PriceRepository priceRepository;

  @MockBean
  private PriceUseCasesImpl priceUseCases;

  @MockBean
  private PriceService priceService;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.initMocks(this);
  }
  @Test
  public void test() throws Exception {

    // Given
    Long brandId = 999L;
    Long productId = 999L;
    LocalDateTime startDate = getLocalDateTime("2020-06-14T00:00:00");
    LocalDateTime endDate = getLocalDateTime("2020-12-31T11:59:59");
    PriceEntity priceEntity = getPriceEntityWithBrandIdProductIdAndStartAndEndDate(brandId, productId, startDate, endDate);
    Price mockPrice = getPriceWithBrandIdProductIdAndStartAndEndDate(brandId, productId, startDate, endDate);
    Price expectedPrice;

    // When;
    when(priceRepository.findByBrandIdAndProductIdAndStartDate(brandId, productId, startDate)).thenReturn(Optional.of(priceEntity));
    when(priceUseCases.findByBrandIdAndProductIdAndStartDate(brandId, productId, startDate)).thenReturn(mockPrice);
    when(priceService.findByBrandIdAndProductIdAndStartDate(brandId, productId, startDate)).thenReturn(mockPrice);

    // When
   mockMvc.perform(get("/api/v1/prices")
                    .param("brand-id","1")
                    .param("product-id","35455")
                    .param("application","2020-06-14T12:00:00"))
            .andExpect(status().isOk())
            .andReturn();
  }

}
