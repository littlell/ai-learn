package com.demo.jvm.basics.function;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.DoubleConsumer;
import java.util.function.IntConsumer;
import java.util.function.LongConsumer;
import java.util.function.ObjDoubleConsumer;
import java.util.function.ObjIntConsumer;

/**
 * 用于消费数据，消费数据类型通过泛型变量决定
 */
public class ConsumerType {
  public static void main(String[] args) {

    Consumer<String> consumer = s -> System.out.println(s);

    BiConsumer<String, String> biConsumer = (a, b) -> System.out.println(a + b);

    DoubleConsumer doubleConsumer = d -> System.out.println(d);

    IntConsumer intConsumer = i -> System.out.println(i);

    LongConsumer longConsumer = l -> System.out.println(l);

    ObjDoubleConsumer<String> objDoubleConsumer = (s, d) -> System.out.println(s + d);

    ObjIntConsumer<String> objIntConsumer = (s, i) -> System.out.println(s + i);
  }
}
