package com.demo.jvm.basics.apache.cxf;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

public class TestMain {


  public static void main(String[] args) {

    String s = UUID.randomUUID().toString().replaceAll("-", "");

    System.out.println(s);

    System.out.println(s.length());

  }
}