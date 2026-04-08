package com.demo.jvm.spring.boot.rsocket.controller;

import com.demo.jvm.spring.boot.rsocket.dto.Alert;
import com.demo.jvm.spring.boot.rsocket.dto.GratuityIn;
import com.demo.jvm.spring.boot.rsocket.dto.GratuityOut;
import com.demo.jvm.spring.boot.rsocket.dto.StockQuote;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/test")
public class TestController {

  private static final Logger log = LoggerFactory.getLogger(TestController.class);

  @Autowired
  private RSocketRequester rSocketRequester;

  @GetMapping("/greeting")
  public Mono<String> handleGreeting() {
    return rSocketRequester
        .route("greeting")
        .data("Hello RSocket!")
        .retrieveMono(String.class);
  }

  @GetMapping("/greeting-name")
  public Mono<String> handleGreetingName() {
    String who = "littlell";
    return rSocketRequester
        .route("greeting/{name}", who)
        .data("Hello RSocket!")
        .retrieveMono(String.class);
  }

  @GetMapping("/stock-price")
  public String stockPrice() {
    String symbol = "sk";
    rSocketRequester
        .route("stock/{symbol}", symbol)
        .retrieveFlux(StockQuote.class)
        .doOnNext(stockQuote ->
            log.info("Price of {} : {} (at {})", stockQuote.getSymbol(), stockQuote.getPrice(), stockQuote.getTimestamp())
        )
        .subscribe();
    return "success";
  }

  @GetMapping("/alert")
  public String sendAlert() {
    rSocketRequester
        .route("alert")
        .data(new Alert(Alert.Level.RED, "littlell", Instant.now()))
        .send()
        .subscribe();
    return "success";
  }

  @GetMapping("/gratuity")
  public String calculateGratuity() throws InterruptedException {

    Flux<GratuityIn> gratuityInFlux = Flux.fromArray(
            new GratuityIn[]{
                new GratuityIn(BigDecimal.valueOf(11.0), 10),
                new GratuityIn(BigDecimal.valueOf(22.0), 20),
                new GratuityIn(BigDecimal.valueOf(33.0), 30),
                new GratuityIn(BigDecimal.valueOf(44.0), 40),
                new GratuityIn(BigDecimal.valueOf(55.0), 50),
                new GratuityIn(BigDecimal.valueOf(66.0), 60)
            })
        .delayElements(Duration.ofSeconds(1));

    rSocketRequester
        .route("gratuity")
        .data(gratuityInFlux)
        .retrieveFlux(GratuityOut.class)
        .subscribe(out -> {
          log.info(out.getPercent() + "% gratuity of " + out.getBillTotal() + " is " + out.getGratuity());
        });
    return "success";
  }
}
