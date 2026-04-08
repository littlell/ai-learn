package com.demo.jvm.spring.core.spel;

public class NumberGuess {
  private double randomNumber;

  public void setRandomNumber(double randomNumber) {
    this.randomNumber = randomNumber;
  }

  @Override
  public String toString() {
    return "NumberGuess{" +
        "randomNumber=" + randomNumber +
        '}';
  }
}
