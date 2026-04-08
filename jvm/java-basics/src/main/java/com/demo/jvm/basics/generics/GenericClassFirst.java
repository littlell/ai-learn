package com.demo.jvm.basics.generics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EmptyStackException;
import java.util.List;

public class GenericClassFirst {
  public static void main(String[] args) {

    Stack<Integer> integerStack = new Stack<>();
    integerStack.push(Integer.valueOf(2));

    // popAll可以传入List<Number>或者List<Integer>, 提高消费类API的灵活性
    List<Number> numberPopResult = new ArrayList<>();
    List<Number> integerPopResult = new ArrayList<>();
    integerStack.popAll(numberPopResult);
    integerStack.popAll(integerPopResult);

    Stack<Number> stack = new Stack<>();
    List<Integer> integersSrc = List.of(1, 2, 3);
    List<Number> numbersSrc = List.of(Integer.valueOf(1), Double.valueOf(2), Float.valueOf(3));

    // pushAll可以传入List<Number>或者List<Integer>, 提高生产类API的灵活性
    stack.pushAll(integersSrc);
    stack.pushAll(numbersSrc);
  }

  static class Stack<E> {
    private E[] elements;

    private int size = 0;

    private static final int DEFAULT_INITIAL_CAPACITY = 16;

    public Stack() {
      // Generic arrays not permitted
      // elements = new E[DEFAULT_INITIAL_CAPACITY];
      elements = (E[]) (new Object[DEFAULT_INITIAL_CAPACITY]);
    }

    public void push(E o) {
      ensureCapacity();

      elements[size++] = o;
    }

    // 生产者 extends
    public void pushAll(List<? extends E> src) {
      src.forEach((e) -> push(e));
    }

    public E pop() {
      if (size == 0) {
        throw new EmptyStackException();
      }

      E result = elements[--size];
      elements[size] = null;

      return result;
    }

    // 消费者 super
    public void popAll(List<? super E> dst) {
      while (!isEmpty()) {
        dst.add(pop());
      }
    }

    public boolean isEmpty() {
      return size == 0;
    }

    private void ensureCapacity() {
      if (size == elements.length) {
        elements = Arrays.copyOf(elements, 2 * size + 1);
      }
    }

    public void printAll() {
      while (!isEmpty()) {
        System.out.println(pop());
      }
    }
  }
}
