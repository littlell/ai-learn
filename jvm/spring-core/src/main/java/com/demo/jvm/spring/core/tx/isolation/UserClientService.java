package com.demo.jvm.spring.core.tx.isolation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserClientService {
  @Autowired
  private UserRepository userRepository;

  @Transactional
  public void insert() {
    userRepository.insert("foo", 15);
  }

  @Transactional(isolation = Isolation.REPEATABLE_READ)
  public Integer selectAgeByName(String name) {
    return userRepository.selectAgeByName(name);
  }
}
