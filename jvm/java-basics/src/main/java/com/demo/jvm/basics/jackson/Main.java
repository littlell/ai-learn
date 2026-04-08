package com.demo.jvm.basics.jackson;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

public class Main {
  public static void main(String[] args) throws IOException {

    InputStream inputStream = Main.class.getClassLoader().getResourceAsStream("demo.json");

    ObjectMapper mapper = new ObjectMapper();
    JsonNode root = mapper.readTree(inputStream);

    // JsonNode转换成java的List对象 方法1（兼容 Jackson 2.6，readerForListOf 需 2.9+）
    List<Pojo> pojoList = mapper.readValue(root.get("groups").traverse(), new TypeReference<List<Pojo>>() {
    });
    System.out.println("JsonNode转换成java的List对象 方法1, " + pojoList);

    // JsonNode转换成java的List对象 方法2
    pojoList = mapper.readValue(root.get("groups").traverse(), new TypeReference<List<Pojo>>() {
    });
    System.out.println("JsonNode转换成java的List对象 方法2, " + pojoList);

    // isValueNode 演示1 array
    JsonNode valueNode1 = root.path("groups");
    System.out.println("isValueNode 演示1 array, " + valueNode1.isValueNode());

    // isValueNode 演示2 object
    JsonNode valueNode2 = root.path("components").path("db");
    System.out.println("isValueNode 演示2 object, " + valueNode2.isValueNode());

    // isValueNode 演示3 missing
    JsonNode valueNode3 = root.path("components").path("db").path("status-missing");
    System.out.println("isValueNode 演示3 missing, " + valueNode3.isValueNode());

    // isValueNode 演示4 value
    JsonNode valueNode4 = root.path("components").path("db").path("status");
    System.out.println("isValueNode 演示4 value, " + valueNode4.isValueNode());

    // isContainerNode 演示1 array
    JsonNode containerNode1 = root.path("groups");
    System.out.println("isContainerNode 演示1 array, " + containerNode1.isContainerNode());

    // isContainerNode 演示2 object
    JsonNode containerNode2 = root.path("components").path("db");
    System.out.println("isContainerNode 演示2 object, " + containerNode2.isContainerNode());

    // fieldNames 演示1
    System.out.println("fieldNames 演示1");
    Iterator<String> nameIterator = root.fieldNames();
    while (nameIterator.hasNext()) {
      System.out.println(nameIterator.next());
    }

    // at 演示1
    JsonNode atNode1 = root.at("/groups/0/readiness");
    System.out.println("at 演示1, " + atNode1);

    // findParent 演示1
    JsonNode findParentNode1 = root.findParent("diskSpace");
    System.out.println("findParent 演示1, " + findParentNode1);

    // findParent 演示2
    List<JsonNode> findParentNode2 = root.findParents("status");
    System.out.println("findParent 演示2, " + findParentNode2);

    // findValue 演示1
    JsonNode findValueNode1 = root.findValue("status");
    System.out.println("findValue 演示1, " + findValueNode1);

    // findValue 演示2
    List<JsonNode> findValueNode2 = root.findValues("status");
    System.out.println("findValue 演示2, " + findValueNode2);

    // hasNonNull 演示1
    boolean hasNonNull1 = root.hasNonNull("status");
    System.out.println("hasNonNull 演示1, " + hasNonNull1);

    // hasNonNull 演示2
    boolean hasNonNull2 = root.hasNonNull("noneStatus");
    System.out.println("hasNonNull 演示2, " + hasNonNull2);
  }
}
