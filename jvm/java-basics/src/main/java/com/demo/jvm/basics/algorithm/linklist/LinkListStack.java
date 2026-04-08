package com.demo.jvm.basics.algorithm.linklist;

import java.util.LinkedList;
import java.util.Stack;

public class LinkListStack<E> extends Stack<E> {
  private LinkedList<E> stack;

  public LinkListStack() {
    this.stack = new LinkedList<E>();
  }

  @Override
  public E push(E item) {
    stack.addFirst(item);
    return item;
  }

  @Override
  public synchronized E pop() {
    return stack.removeFirst();
  }

  @Override
  public synchronized E peek() {
    return stack.getFirst();
  }

  @Override
  public boolean empty() {
    return stack.isEmpty();
  }

  @Override
  public synchronized int search(Object o) {
    return stack.indexOf(o) + 1;
  }

  public static void main(String[] args) {

    Stack<Integer> stack = new Stack<>();
    LinkListStack<Integer> linkListStack = new LinkListStack<>();

    System.out.println("push");
    System.out.println(stack.push(1));
    System.out.println(linkListStack.push(1));

    System.out.println("peek");
    System.out.println(stack.peek());
    System.out.println(linkListStack.peek());

    System.out.println("pop");
    System.out.println(stack.pop());
    System.out.println(linkListStack.pop());

    System.out.println("empty");
    System.out.println(stack.empty());
    System.out.println(linkListStack.empty());

    System.out.println("search");
    stack.push(1);
    stack.push(2);
    stack.push(3);
    stack.push(4);
    linkListStack.push(1);
    linkListStack.push(2);
    linkListStack.push(3);
    linkListStack.push(4);
    System.out.println(stack.search(1));
    System.out.println(linkListStack.search(1));
  }
}
