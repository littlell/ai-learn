package com.demo.jvm.spring.boot.elasticsearch;

import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * MegaCorp Repository 接口 - 优化方法签名和参数命名
 * 使用更语义化的参数名和 List 接口替代具体实现类
 */
public interface MegaCorpRepository extends ElasticsearchRepository<MegaCorp, String> {

  List<MegaCorp> findByFirstNameAndLastName(String firstName, String lastName);

  List<MegaCorp> findByFirstNameOrLastName(String firstName, String lastName);

  List<MegaCorp> findByFirstName(String firstName);

  List<MegaCorp> findByFirstNameNot(String firstName);

  List<MegaCorp> findByAgeBetween(Integer minAge, Integer maxAge);

  List<MegaCorp> findByAgeLessThanEqual(Integer age);

  List<MegaCorp> findByAgeGreaterThanEqual(Integer age);

  List<MegaCorp> findByAgeBefore(Integer age);

  List<MegaCorp> findByAgeAfter(Integer age);

  List<MegaCorp> findByFirstNameLike(String firstNamePattern);

  List<MegaCorp> findByFirstNameStartingWith(String prefix);

  List<MegaCorp> findByFirstNameEndingWith(String suffix);

  List<MegaCorp> findByFirstNameContaining(String substring);

  List<MegaCorp> findByFirstNameIn(List<String> firstNames);

  List<MegaCorp> findByFirstNameNotIn(List<String> firstNames);

  List<MegaCorp> findByAgeGreaterThanEqualOrderByAgeAsc(Integer minAge);

  @Query("{\"bool\" : {\"must\" : {\"match\" : {\"lastName\" : \"?0\"}}}}")
  List<MegaCorp> findByLastName(String lastName);
}
