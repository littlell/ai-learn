package com.demo.jvm.basics.design.pattern.composite;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/** Created by littlell on 2017/4/9. */
public class Menu extends MenuComponent {
  private String name;
  private String description;
  private List<MenuComponent> components = new ArrayList<>();

  public Menu(String name, String description) {
    this.name = name;
    this.description = description;
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
  void add(MenuComponent component) {
    components.add(component);
  }

  @Override
  void delete(MenuComponent component) {
    components.remove(component);
  }

  @Override
  MenuComponent getChild(int i) {
    return components.get(i);
  }

  @Override
  Iterator createIterator() {
    return new CompositeIterator(components.iterator());
  }

  @Override
  void print() {
    System.out.println(toString());
    Iterator iterator = components.iterator();
    while (iterator.hasNext()) {
      MenuComponent component = (MenuComponent) iterator.next();
      component.print();
    }
  }

  @Override
  public String toString() {
    return "Menu{" + "name='" + name + '\'' + ", description='" + description + '\'' + '}';
  }
}
