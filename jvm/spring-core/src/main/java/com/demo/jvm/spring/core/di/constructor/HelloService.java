package com.demo.jvm.spring.core.di.constructor;

public class HelloService {
  private Hello01Dao hello01Dao;
  private Hello02Dao hello02Dao;

  public HelloService(Hello01Dao hello01Dao, Hello02Dao hello02Dao) {
    this.hello01Dao = hello01Dao;
    this.hello02Dao = hello02Dao;
  }

  @Override
  public String toString() {
    return "HelloService{" +
        "hello01Dao=" + hello01Dao +
        ", hello02Dao=" + hello02Dao +
        '}';
  }
}
