package com.demo.jvm.spring.core.tx.propagation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServerService {
  @Autowired
  private UserRepository userRepository;

  @Transactional(propagation = Propagation.REQUIRED)
  public void insertSingleUserRequired() {
    userRepository.insert("foo_required", 15);
    throw new RuntimeException("error!");
  }

  @Transactional(propagation = Propagation.REQUIRES_NEW)
  public void insertSingleUserRequiredNew() {
    userRepository.insert("foo_required_new", 15);
    throw new RuntimeException("error!");
  }

  @Transactional(propagation = Propagation.SUPPORTS)
  public void insertSingleUserSupport() {
    userRepository.insert("foo_support", 15);
    throw new RuntimeException("error!");
  }

  @Transactional(propagation = Propagation.MANDATORY)
  public void insertSingleUserMandatory() {
    userRepository.insert("foo_mandatory", 15);
    throw new RuntimeException("error!");
  }

  @Transactional(propagation = Propagation.NOT_SUPPORTED)
  public void insertSingleUserNotSupported() {
    userRepository.insert("foo_not_supported", 15);
    throw new RuntimeException("error!");
  }

  @Transactional(propagation = Propagation.NEVER)
  public void insertSingleUserNever() {
    userRepository.insert("foo_never", 15);
    throw new RuntimeException("error!");
  }
}
