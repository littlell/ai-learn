package com.demo.jvm.basics.design.pattern.composite;

import java.util.Iterator;

/** Created by littlell on 2017/4/9. */
public class MenuItem extends MenuComponent {
  private String name;
  private String description;
  private String price;
  private boolean isVegetarian;

  /**
   * constructor.
   */
  public MenuItem(String name, String description, String price, boolean isVegetarian) {
    this.name = name;
    this.description = description;
    this.price = price;
    this.isVegetarian = isVegetarian;
  }

  @Override
  String getName() {
    return name;
  }

  @Override
  String getDescription() {
    return description;
  }

  @Override
  String getPrice() {
    return price;
  }

  @Override
  boolean isVegetarian() {
    return isVegetarian;
  }

  @Override
  void print() {
    System.out.println(toString());
  }

  @Override
  public String toString() {
    return "MenuItem{"
        + "name='"
        + name
        + '\''
        + ", description='"
        + description
        + '\''
        + ", price='"
        + price
        + '\''
        + ", isVegetarian="
        + isVegetarian
        + '}';
  }

  @Override
  Iterator createIterator() {

    return new NullIterator();
  }
}
