package com.demo.jvm.basics.design.pattern.composite;

import java.util.Iterator;

/** Created by littlell on 20 17/4/9. */
public abstract class MenuComponent {
  /**
   * 获取名称.
   *
   */
  String getName() {
    throw new UnsupportedOperationException();
  }

  /**
   * 描述.
   *
   */
  String getDescription() {
    throw new UnsupportedOperationException();
  }

  /**
   * 价格.
   *
   */
  String getPrice() {
    throw new UnsupportedOperationException();
  }

  /**
   * 是否是素食.
   *
   */
  boolean isVegetarian() {
    throw new UnsupportedOperationException();
  }

  /**
   * 增加元素.
   *
   */
  void add(MenuComponent component) {
    throw new UnsupportedOperationException();
  }

  /**
   * 减少元素.
   *
   */
  void delete(MenuComponent component) {
    throw new UnsupportedOperationException();
  }

  /**
   * 获取元素.
   *
   */
  MenuComponent getChild(int i) {
    throw new UnsupportedOperationException();
  }

  /** 打印. */
  void print() {
    throw new UnsupportedOperationException();
  }

  /**
   * 创建迭代器.
   *
   */
  Iterator createIterator() {
    throw new UnsupportedOperationException();
  }
}
