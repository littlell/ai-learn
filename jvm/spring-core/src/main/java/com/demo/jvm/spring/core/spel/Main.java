package com.demo.jvm.spring.core.spel;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

public class Main {
  public static void main(String[] args) {

    ExpressionParser parser = new SpelExpressionParser();
    Expression exp = parser.parseExpression("'Hello World'");
    String message = (String) exp.getValue();
    System.out.println(message);

    exp = parser.parseExpression("'Hello World'.concat('!')");
    message = (String) exp.getValue();
    System.out.println(message);

    exp = parser.parseExpression("'Hello World'.bytes");
    byte[] bytes = (byte[]) exp.getValue();
    System.out.println(bytes);

    exp = parser.parseExpression("'Hello World'.bytes.length");
    int length = (Integer) exp.getValue();
    System.out.println(length);

    exp = parser.parseExpression("new String('hello world').toUpperCase()");
    message = exp.getValue(String.class);
    System.out.println(message);

    ApplicationContext context = new ClassPathXmlApplicationContext("spel/applicationContext.xml");
    System.out.println(context.getBean("user"));
    System.out.println(context.getBean("numberGuess"));

  }

}
