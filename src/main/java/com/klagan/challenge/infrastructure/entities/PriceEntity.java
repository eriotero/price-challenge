package com.klagan.challenge.infrastructure.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "PRICES")
public class PriceEntity implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "BRAND_ID", nullable = false)
  private Long brandId;

  @Column(name = "START_DATE", nullable = false)
  @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
  @Temporal(TemporalType.TIMESTAMP)
  private LocalDateTime startDate;

  @Column(name = "END_DATE", nullable = false)
  @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
  @Temporal(TemporalType.TIMESTAMP)
  private LocalDateTime endDate;

  @Column(name = "PRICE_LIST", nullable = false)
  private int priceList;

  @Column(name = "PRODUCT_ID", nullable = false)
  private Long productId;

  @Column(name = "PRIORITY", nullable = false)
  private int priority;

  @Column(name = "PRICE", nullable = false)
  private double price;

  @Column(name = "CURRENCY", nullable = false, length = 3)
  private String currency;

}
