package com.demo.jvm.basics.design.pattern.composite;

import java.util.Iterator;

/** Created by littlell on 2017/4/9. */
public class NullIterator implements Iterator {
  @Override
  public boolean hasNext() {
    return false;
  }

  @Override
  public Object next() {
    return null;
  }

  @Override
  public void remove() {
    throw new UnsupportedOperationException();
  }
}
