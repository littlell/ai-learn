package com.demo.jvm.basics.reactor;

import java.util.Arrays;
import java.util.List;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;
import reactor.test.StepVerifier;

public class FlatMapDemo {
  public static void main(String[] args) {

    Flux<Player> playerFlux = Flux.just("Michael Jordan", "Scottie Pippen", "Steve Kerr")
        .flatMap(n -> Mono.just(n)
            .map(p -> {
              String[] split = p.split("\\s");
              return new Player(split[0], split[1]);
            })
            .subscribeOn(Schedulers.parallel()));

    List<Player> playerList = Arrays.asList(
        new Player("Michael", "Jordan"),
        new Player("Scottie", "Pippen"),
        new Player("Steve", "Kerr")
    );

    StepVerifier.create(playerFlux)
        .expectNextMatches(p -> playerList.contains(p))
        .expectNextMatches(p -> playerList.contains(p))
        .expectNextMatches(p -> playerList.contains(p))
        .verifyComplete();
  }

  static class Player {
    private final String firstName;
    private final String lastName;

    public String getFirstName() {
      return firstName;
    }

    public String getLastName() {
      return lastName;
    }

    public Player(String firstName, String lastName) {
      this.firstName = firstName;
      this.lastName = lastName;
    }

    @Override
    public boolean equals(Object obj) {
      if (obj instanceof Player) {
        if (((Player) obj).getFirstName().equals(firstName) && ((Player) obj).getLastName().equals(lastName)) {
          return true;
        }
      }
      return false;
    }
  }
}
