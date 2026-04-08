package com.demo.jvm.spring.boot.rsocket.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.codec.cbor.Jackson2CborDecoder;
import org.springframework.http.codec.cbor.Jackson2CborEncoder;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.messaging.rsocket.RSocketStrategies;
import org.springframework.web.util.pattern.PathPatternRouteMatcher;

import java.net.URI;

@Configuration
public class ClientConfiguration {

  @Bean
  public RSocketStrategies rSocketStrategies() {
    return RSocketStrategies.builder()
        .encoders(encoders -> encoders.add(new Jackson2CborEncoder()))
        .decoders(decoders -> decoders.add(new Jackson2CborDecoder()))
        .routeMatcher(new PathPatternRouteMatcher())
        .build();
  }

  @Bean
  public RSocketRequester rSocketRequester(RSocketStrategies rSocketStrategies) {
    RSocketRequester.Builder builder = RSocketRequester.builder();
    builder.rsocketStrategies(rSocketStrategies);
    // 使用TCP协议通信时指定
    // return builder.tcp("localhost", 7000);
    // 使用HTTP协议通信时指定
    return builder.websocket(URI.create("ws://localhost:8080/rsocket"));
  }
}
