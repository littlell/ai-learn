package com.demo.jvm.basics.design.pattern.observer;

/** Created by xialei on 2016/12/31. */
public interface Subject {
  void registerObserver(Observer observer);

  void removeObserver(Observer observer);

  void notifyObservers();
}
