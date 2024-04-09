package com.klagan.challenge.infrastructure.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;
import com.klagan.challenge.application.services.PriceService;
import com.klagan.challenge.application.usecases.PriceUseCasesImpl;
import com.klagan.challenge.domain.ports.input.PriceUseCases;
import com.klagan.challenge.domain.ports.output.PriceRepositoryPort;
import com.klagan.challenge.infrastructure.adapters.PriceRepositoryH2Adapter;
import com.klagan.challenge.infrastructure.repositories.PriceRepository;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;

@Configuration
public class ApplicationConfig {

  @Bean
  public PriceRepositoryH2Adapter PriceRepositoryH2Adapter(final PriceRepository priceRepository){
    return new PriceRepositoryH2Adapter(priceRepository);
  }
  @Bean
  public PriceUseCases getPriceUseCases(final PriceRepositoryPort priceRepositoryPort, final ModelMapper mapper){
    return new PriceUseCasesImpl(priceRepositoryPort, mapper);
  }
  @Bean
  public PriceService getPriceService(final PriceUseCases priceUseCases){
    return new PriceService(priceUseCases);
  }

  @Bean
  public ModelMapper getModelMapper(){ return new ModelMapper(); }

  @Bean
  public ObjectMapper getObjectMapper(){
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    ObjectMapper mapper = new ObjectMapper();
    mapper.registerModule(new JSR310Module());
    mapper.setDateFormat(df);
    return mapper;
  }

}
