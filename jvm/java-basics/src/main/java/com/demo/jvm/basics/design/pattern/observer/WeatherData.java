package com.demo.jvm.basics.design.pattern.observer;

import java.util.ArrayList;
import java.util.List;

public class WeatherData implements Subject {
  private List<Observer> observerList;
  private float temperature;
  private float humidity;
  private float pressure;

  public WeatherData() {
    observerList = new ArrayList<>();
  }

  @Override
  public void registerObserver(Observer observer) {
    observerList.add(observer);
  }

  @Override
  public void removeObserver(Observer observer) {
    observerList.remove(observer);
  }

  @Override
  public void notifyObservers() {
    for (Observer observer : observerList) {
      observer.update(temperature, humidity, pressure);
    }
  }

  /** Created by xialei on 2016/12/31. */
  public static void main(String[] args) {
    Subject weatherData = new WeatherData();
    Observer observer1 = new StatisticsDisplay(weatherData);
    Observer observer2 = new ForecastDisplay(weatherData);

    weatherData.notifyObservers();
  }
}
