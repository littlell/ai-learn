package com.demo.jvm.spring.boot.rsocket.controller;

import com.demo.jvm.spring.boot.rsocket.dto.StockQuote;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;

@Controller
public class StockController {

  /**
   * rsocket-cli --request --route=stock/sk  ws://127.0.0.1:8080/rsocket
   */
  @MessageMapping("stock/{symbol}")
  public Flux<StockQuote> getStockPrice(
      @DestinationVariable("symbol") String symbol) {
    return Flux
        .interval(Duration.ofSeconds(1))
        .map(i -> {
          BigDecimal price = BigDecimal.valueOf(Math.random() * 10);
          return new StockQuote(symbol, price, Instant.now());
        })
        .take(3);

  }
}
