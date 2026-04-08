package com.demo.jvm.spring.core.tx.propagation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserClientService {
  @Autowired
  private UserRepository userRepository;

  @Autowired
  private UserServerService userServerService;

  @Transactional
  public void propagationRequire() {
    userRepository.insert("foo_required", 15);
    try {
      userServerService.insertSingleUserRequired();
    } catch (Exception e) {
      System.out.println("error happened.");
    }
  }

  @Transactional
  public void propagationRequireNew() {
    userRepository.insert("foo_required_new", 15);
    try {
      userServerService.insertSingleUserRequiredNew();
    } catch (Exception e) {
      System.out.println("error happened.");
    }
  }

  @Transactional
  public void propagationSupport() {
    userRepository.insert("foo_support", 15);
    try {
      userServerService.insertSingleUserSupport();
    } catch (Exception e) {
      System.out.println("error happened.");
    }
  }

  @Transactional
  public void propagationMandatory() {
    userRepository.insert("foo_mandatory", 15);
    try {
      userServerService.insertSingleUserMandatory();
    } catch (Exception e) {
      System.out.println("error happened.");
    }
  }

  @Transactional
  public void propagationNotSupported() {
    userRepository.insert("foo_not_supported", 15);
    try {
      userServerService.insertSingleUserNotSupported();
    } catch (Exception e) {
      System.out.println("error happened.");
    }
  }

  @Transactional
  public void propagationNotNever() {
    userRepository.insert("foo_never", 15);
    try {
      userServerService.insertSingleUserNever();
    } catch (Exception e) {
      System.out.println("error happened.");
    }
  }
}
