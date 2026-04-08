package com.demo.jvm.spring.boot.mongodb.repository;

import com.demo.jvm.spring.boot.mongodb.entity.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import java.util.List;

public interface CustomerRepository extends MongoRepository<Customer, String> {

  Customer findByFirstName(String firstName);

  List<Customer> findByLastName(String lastName);

  List<Customer> findByFirstNameAndLastName(String firstName, String lastName);

  Customer findByEmail(String email);

  List<Customer> findByAge(Integer age);

  @Query("{'age': {$gte: ?0, $lte: ?1}}")
  List<Customer> findByAgeBetween(Integer minAge, Integer maxAge);

  @Query("{'firstName': {$regex: ?0, $options: 'i'}}")
  List<Customer> findByFirstNameContainingIgnoreCase(String firstName);
}
