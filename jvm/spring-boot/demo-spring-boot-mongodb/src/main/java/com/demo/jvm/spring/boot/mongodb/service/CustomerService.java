package com.demo.jvm.spring.boot.mongodb.service;

import com.demo.jvm.spring.boot.mongodb.entity.Customer;
import com.demo.jvm.spring.boot.mongodb.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

  @Autowired
  private CustomerRepository customerRepository;

  /**
   * 保存客户信息
   */
  public Customer saveCustomer(Customer customer) {
    return customerRepository.save(customer);
  }

  /**
   * 获取所有客户
   */
  public List<Customer> getAllCustomers() {
    return customerRepository.findAll();
  }

  /**
   * 根据ID获取客户
   */
  public Optional<Customer> getCustomerById(String id) {
    return customerRepository.findById(id);
  }

  /**
   * 根据名字查找客户
   */
  public Customer getCustomerByFirstName(String firstName) {
    return customerRepository.findByFirstName(firstName);
  }

  /**
   * 根据姓氏查找客户
   */
  public List<Customer> getCustomersByLastName(String lastName) {
    return customerRepository.findByLastName(lastName);
  }

  /**
   * 根据邮箱查找客户
   */
  public Customer getCustomerByEmail(String email) {
    return customerRepository.findByEmail(email);
  }

  /**
   * 根据年龄查找客户
   */
  public List<Customer> getCustomersByAge(Integer age) {
    return customerRepository.findByAge(age);
  }

  /**
   * 根据年龄范围查找客户
   */
  public List<Customer> getCustomersByAgeBetween(Integer minAge, Integer maxAge) {
    return customerRepository.findByAgeBetween(minAge, maxAge);
  }

  /**
   * 根据名字模糊查找客户
   */
  public List<Customer> getCustomersByFirstNameContaining(String firstName) {
    return customerRepository.findByFirstNameContainingIgnoreCase(firstName);
  }

  /**
   * 根据姓名查找客户
   */
  public List<Customer> getCustomersByFullName(String firstName, String lastName) {
    return customerRepository.findByFirstNameAndLastName(firstName, lastName);
  }

  /**
   * 删除客户
   */
  public void deleteCustomer(String id) {
    customerRepository.deleteById(id);
  }

  /**
   * 删除所有客户
   */
  public void deleteAllCustomers() {
    customerRepository.deleteAll();
  }

  /**
   * 检查客户是否存在
   */
  public boolean customerExists(String id) {
    return customerRepository.existsById(id);
  }

  /**
   * 获取客户总数
   */
  public long getCustomerCount() {
    return customerRepository.count();
  }
}

