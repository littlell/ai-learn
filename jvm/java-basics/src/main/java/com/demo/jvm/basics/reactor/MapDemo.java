package com.demo.jvm.basics.reactor;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class MapDemo {
  public static void main(String[] args) {

    Flux<Player> playerFlux = Flux.just("Michael Jordan", "Scottie Pippen", "Steve Kerr")
        .map(n -> {
          String[] split = n.split("\\s");
          return new Player(split[0], split[1]);
        });

    StepVerifier.create(playerFlux)
        .expectNext(new Player("Michael", "Jordan"))
        .expectNext(new Player("Scottie", "Pippen"))
        .expectNext(new Player("Steve", "Kerr"))
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
