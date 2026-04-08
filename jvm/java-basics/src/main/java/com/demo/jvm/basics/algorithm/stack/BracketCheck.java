package com.demo.jvm.basics.algorithm.stack;

import java.util.Stack;

public class BracketCheck {
  public static void main(String[] args) {

    String bracketsStr1 = "()({}){([()[]])}";

    String bracketsStr2 = "([]{)";

    String bracketsStr3 = ")([()]{}";

    String bracketsStr4 = "([())]{}";

    System.out.println(bracketCheck(bracketsStr1));
    System.out.println(bracketCheck(bracketsStr2));
    System.out.println(bracketCheck(bracketsStr3));
    System.out.println(bracketCheck(bracketsStr4));
  }

  public static boolean bracketCheck(String src) {

    Stack<Character> stack = new Stack<>();

    for (Character c : src.toCharArray()) {
      if (c.equals('(') || c.equals('[') || c.equals('{')) {
        stack.push(c);
      } else {
        if (stack.isEmpty()) {
          return false;
        }
        if (c.equals(')') && !stack.pop().equals('(')) {
          return false;
        }
        if (c.equals(']') && !stack.pop().equals('[')) {
          return false;
        }
        if (c.equals('}') && !stack.pop().equals('{')) {
          return false;
        }
      }
    }

    return true;
  }
}