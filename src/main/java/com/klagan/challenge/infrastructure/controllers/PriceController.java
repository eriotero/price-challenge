package com.klagan.challenge.infrastructure.controllers;

import com.klagan.challenge.application.services.PriceService;
import com.klagan.challenge.domain.models.Price;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/prices")
public class PriceController {

  private final PriceService priceService;

  public PriceController(PriceService priceService) {
    this.priceService = priceService;
  }

  @GetMapping
  public ResponseEntity<Price> getPriceByIdAndApplicationDateAndProductId(
          @RequestParam("application") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime applicationDate,
          @RequestParam("product-id") long productId,
          @RequestParam("brand-id") long brandId
  ){

    Price price = priceService.findByBrandIdAndProductIdAndStartDate(brandId, productId, applicationDate);
    return ResponseEntity.ok().body(price);
  }
}
