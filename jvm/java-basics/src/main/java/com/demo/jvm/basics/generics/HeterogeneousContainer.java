package com.demo.jvm.basics.generics;

import java.util.HashMap;
import java.util.Map;

public class HeterogeneousContainer {

  public static void main(String[] args) {
    Favorites favorites = new Favorites();

    favorites.putFavorite(String.class, "hello");
    favorites.putFavorite(Integer.class, 1);
    favorites.putFavorite(Class.class, Double.class);

    String s = favorites.getFavorite(String.class);
    Integer i = favorites.getFavorite(Integer.class);
    Class c = favorites.getFavorite(Class.class);

    System.out.println(s);
    System.out.println(i);
    System.out.println(c);
  }

  static class Favorites {

    private Map<Class<?>, Object> favorites = new HashMap<>();

    <T> void putFavorite(Class<T> tClass, T instance) {
      favorites.put(tClass, instance);

      favorites.put(String.class, 1);
    }

    <T> T getFavorite(Class<T> tClass) {
      return tClass.cast(favorites.get(tClass));
    }

    @Override
    public String toString() {
      return "Favorites{" +
          "favorites=" + favorites +
          '}';
    }
  }

}
