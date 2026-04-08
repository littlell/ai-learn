package com.demo.jvm.basics.design.pattern.observer;

/** Created by xialei on 2016/12/31. */
public class StatisticsDisplay implements Observer, DisplayElement {
  private Subject weatherData;
  private float temperature;
  private float humidity;
  private float pressure;

  public StatisticsDisplay(Subject weatherData) {
    this.weatherData = weatherData;
    weatherData.registerObserver(this);
  }

  @Override
  public void update(float temp, float humidity, float pressure) {
    this.temperature = temp;
    this.humidity = humidity;
    this.pressure = pressure;
    display();
  }

  @Override
  public void display() {
    System.out.println("StatisticsDisplay");
  }
}
