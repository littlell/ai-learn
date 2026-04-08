package com.demo.jvm.spring.boot.mongodb.controller;

import com.demo.jvm.spring.boot.mongodb.entity.Customer;
import com.demo.jvm.spring.boot.mongodb.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

  @Autowired
  private CustomerService customerService;

  /**
   * 创建新客户
   */
  @PostMapping
  public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
    Customer savedCustomer = customerService.saveCustomer(customer);
    return ResponseEntity.ok(savedCustomer);
  }

  /**
   * 获取所有客户
   */
  @GetMapping
  public ResponseEntity<List<Customer>> getAllCustomers() {
    List<Customer> customers = customerService.getAllCustomers();
    return ResponseEntity.ok(customers);
  }

  /**
   * 根据ID获取客户
   */
  @GetMapping("/{id}")
  public ResponseEntity<Customer> getCustomerById(@PathVariable String id) {
    Optional<Customer> customer = customerService.getCustomerById(id);
    return customer.map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  /**
   * 根据名字查找客户
   */
  @GetMapping("/firstName/{firstName}")
  public ResponseEntity<Customer> getCustomerByFirstName(@PathVariable String firstName) {
    Customer customer = customerService.getCustomerByFirstName(firstName);
    if (customer != null) {
      return ResponseEntity.ok(customer);
    }
    return ResponseEntity.notFound().build();
  }

  /**
   * 根据姓氏查找客户
   */
  @GetMapping("/lastName/{lastName}")
  public ResponseEntity<List<Customer>> getCustomersByLastName(@PathVariable String lastName) {
    List<Customer> customers = customerService.getCustomersByLastName(lastName);
    return ResponseEntity.ok(customers);
  }

  /**
   * 根据邮箱查找客户
   */
  @GetMapping("/email/{email}")
  public ResponseEntity<Customer> getCustomerByEmail(@PathVariable String email) {
    Customer customer = customerService.getCustomerByEmail(email);
    if (customer != null) {
      return ResponseEntity.ok(customer);
    }
    return ResponseEntity.notFound().build();
  }

  /**
   * 根据年龄查找客户
   */
  @GetMapping("/age/{age}")
  public ResponseEntity<List<Customer>> getCustomersByAge(@PathVariable Integer age) {
    List<Customer> customers = customerService.getCustomersByAge(age);
    return ResponseEntity.ok(customers);
  }

  /**
   * 根据年龄范围查找客户
   */
  @GetMapping("/age/between")
  public ResponseEntity<List<Customer>> getCustomersByAgeBetween(
      @RequestParam Integer minAge, 
      @RequestParam Integer maxAge) {
    List<Customer> customers = customerService.getCustomersByAgeBetween(minAge, maxAge);
    return ResponseEntity.ok(customers);
  }

  /**
   * 根据名字模糊查找客户
   */
  @GetMapping("/search/firstName/{firstName}")
  public ResponseEntity<List<Customer>> searchCustomersByFirstName(@PathVariable String firstName) {
    List<Customer> customers = customerService.getCustomersByFirstNameContaining(firstName);
    return ResponseEntity.ok(customers);
  }

  /**
   * 根据姓名查找客户
   */
  @GetMapping("/fullName")
  public ResponseEntity<List<Customer>> getCustomersByFullName(
      @RequestParam String firstName, 
      @RequestParam String lastName) {
    List<Customer> customers = customerService.getCustomersByFullName(firstName, lastName);
    return ResponseEntity.ok(customers);
  }

  /**
   * 更新客户信息
   */
  @PutMapping("/{id}")
  public ResponseEntity<Customer> updateCustomer(@PathVariable String id, @RequestBody Customer customer) {
    Optional<Customer> existingCustomer = customerService.getCustomerById(id);
    if (existingCustomer.isPresent()) {
      customer.setId(id);
      Customer updatedCustomer = customerService.saveCustomer(customer);
      return ResponseEntity.ok(updatedCustomer);
    }
    return ResponseEntity.notFound().build();
  }

  /**
   * 删除客户
   */
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteCustomer(@PathVariable String id) {
    if (customerService.customerExists(id)) {
      customerService.deleteCustomer(id);
      return ResponseEntity.ok().build();
    }
    return ResponseEntity.notFound().build();
  }

  /**
   * 删除所有客户
   */
  @DeleteMapping
  public ResponseEntity<Void> deleteAllCustomers() {
    customerService.deleteAllCustomers();
    return ResponseEntity.ok().build();
  }

  /**
   * 获取客户总数
   */
  @GetMapping("/count")
  public ResponseEntity<Long> getCustomerCount() {
    long count = customerService.getCustomerCount();
    return ResponseEntity.ok(count);
  }
}

