package com.demo.jvm.spring.core.annotation.config;

import java.util.Optional;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.lang.Nullable;

public class OrderService {

  @Autowired
  private AddressService addressService;

  private ShipService shipService;

  private PayService payService;

  private AuditService auditService;

  private UserService userService;

  @Autowired
  private ApplicationContext context;

  public void setPayService(PayService payService) {
    this.payService = payService;
  }

  @Autowired
  @Qualifier("primary")
  public void setShipService(ShipService shipService) {
    this.shipService = shipService;
  }

  @Resource
  public void setAuditService(AuditService auditService) {
    this.auditService = auditService;
  }

  @Autowired(required = false)
  public void setUserService(UserService userService) {
    this.userService = userService;
  }

//  @Autowired
//  public void setUserService(Optional<UserService> userService) {
//  }

//  @Autowired
//  public void setUserService(@Nullable UserService userService) {
//    this.userService = userService;
//  }
}
