package com.demo.jvm.basics.design.pattern.composite;

import java.util.Iterator;

public class Client {
  MenuComponent component;

  public Client(MenuComponent component) {
    this.component = component;
  }

  public void print() {
    component.print();
  }

  /**
   * 打印蔬菜菜单.
   */
  public void printVegetableItems() {
    Iterator iterator = component.createIterator();
    int i = 0;
    while (iterator.hasNext()) {
      MenuComponent component = (MenuComponent) iterator.next();
      System.out.println(++i);
      System.out.println(component.toString());
      try {
        if (component.isVegetarian()) {
          System.out.println(component.getName());
        }
      } catch (UnsupportedOperationException e) {
        e.printStackTrace();
      }
    }
  }

  /** Created by littlell on 2017/4/9. */
  public static void main(String[] args) {
    MenuComponent indianMenu = new Menu("Indian Menu", "Big Deal lunch");

    indianMenu.add(new MenuItem("Indian chicken", "good", "3", false));
    indianMenu.add(new MenuItem("Indian vegetable", "good", "3", true));

    MenuComponent chinaMenu = new Menu("China food", "dinner");

    chinaMenu.add(new MenuItem("china noodles", "good", "4", true));

    MenuComponent chinaSubMenu = new Menu("China sub menu", "china sub menu");

    chinaSubMenu.add(new MenuItem("china meat", "good meat", "1", false));
    chinaSubMenu.add(new MenuItem("china vegetable", "good vegetable", "2", true));
    chinaSubMenu.add(new MenuItem("china vegetable1", "good vegetable1", "3", true));

    chinaMenu.add(chinaSubMenu);

    MenuComponent allMenus = new Menu("All menus", "all food and drink");

    allMenus.add(indianMenu);
    allMenus.add(chinaMenu);

    Client client = new Client(allMenus);

    client.print();

    System.out.println("vegetables are:");

    client.printVegetableItems();
  }
}
