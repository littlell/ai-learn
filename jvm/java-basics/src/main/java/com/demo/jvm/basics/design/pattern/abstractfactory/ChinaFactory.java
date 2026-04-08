package com.demo.jvm.basics.design.pattern.abstractfactory;

public class ChinaFactory implements MediaFactory {

  public Film getFilm() {
    return new ChinaFilm();
  }

  @Override
  public Music getMusic() {
    return new ChinaMusic();
  }
}
