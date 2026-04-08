package com.demo.jvm.spring.core.annotation.scanning;

import com.demo.jvm.spring.core.annotation.scanning.regex.StockDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HelloService {
  @Autowired
  private HelloDao helloDao;

  @Autowired
  private AddressDao addressDao;

  @Autowired
  private StockDao stockDao;

  @Override
  public String toString() {
    return "HelloService{" +
        "helloDao=" + helloDao +
        '}';
  }
}
