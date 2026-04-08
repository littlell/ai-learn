package com.demo.jvm.spring.boot.elasticsearch;

import org.springframework.data.elasticsearch.client.elc.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * MegaCorp Controller - 优化使用构造函数注入和现代语法
 * 移除了 @Autowired 字段注入，使用构造函数注入
 * 使用 List.of() 替代匿名内部类
 */
@RestController
@RequestMapping("/megacorp")
public class MegaCorpController {

  private final MegaCorpRepository megaCorpRepository;
  private final ElasticsearchTemplate elasticsearchTemplate;

  public MegaCorpController(MegaCorpRepository megaCorpRepository,
                            ElasticsearchTemplate elasticsearchTemplate) {
    this.megaCorpRepository = megaCorpRepository;
    this.elasticsearchTemplate = elasticsearchTemplate;
  }

  @GetMapping("/and")
  public Object and() {
    return megaCorpRepository.findByFirstNameAndLastName("Jane", "Smith");
  }

  @GetMapping("/or")
  public Object or() {
    return megaCorpRepository.findByFirstNameOrLastName("Jane", "Jane");
  }

  @GetMapping("/is")
  public Object is() {
    return megaCorpRepository.findByFirstName("Jane");
  }

  @GetMapping("/not")
  public Object not() {
    return megaCorpRepository.findByFirstNameNot("Jane");
  }

  @GetMapping("/between")
  public Object between() {
    return megaCorpRepository.findByAgeBetween(1, 30);
  }

  @GetMapping("/lessThanEqual")
  public Object lessThanEqual() {
    return megaCorpRepository.findByAgeLessThanEqual(100);
  }

  @GetMapping("/greaterThanEqual")
  public Object greaterThanEqual() {
    return megaCorpRepository.findByAgeGreaterThanEqual(1);
  }

  @GetMapping("/before")
  public Object before() {
    return megaCorpRepository.findByAgeBefore(100);
  }

  @GetMapping("/after")
  public Object after() {
    return megaCorpRepository.findByAgeAfter(1);
  }

  @GetMapping("/like")
  public Object like() {
    return megaCorpRepository.findByFirstNameLike("Jan");
  }

  @GetMapping("/startingWith")
  public Object startingWith() {
    return megaCorpRepository.findByFirstNameStartingWith("Ja");
  }

  @GetMapping("/endingWith")
  public Object endingWith() {
    return megaCorpRepository.findByFirstNameEndingWith("ne");
  }

  @GetMapping("/containing")
  public Object containing() {
    return megaCorpRepository.findByFirstNameContaining("an");
  }

  @GetMapping("/in")
  public Object in() {
    return megaCorpRepository.findByFirstNameIn(List.of("a", "b", "Jane"));
  }

  @GetMapping("/notIn")
  public Object notIn() {
    return megaCorpRepository.findByFirstNameNotIn(List.of("a", "b", "Jane"));
  }

  @GetMapping("/orderBy")
  public Object orderBy() {
    return megaCorpRepository.findByAgeGreaterThanEqualOrderByAgeAsc(1);
  }

  @GetMapping("/annotation")
  public Object annotation() {
    return megaCorpRepository.findByLastName("Smith");
  }

  // ============== 动态查询示例 ==============

  /**
   * 动态查询示例1：根据条件动态构建查询
   * 示例URL：/megacorp/dynamic/simple?firstName=Jane&minAge=25
   */
  @GetMapping("/dynamic/simple")
  public List<MegaCorp> dynamicSearchSimple(
      @RequestParam(required = false) String firstName,
      @RequestParam(required = false) Integer minAge) {

    Criteria criteria = new Criteria();

    // 根据参数动态添加查询条件
    if (firstName != null && !firstName.trim().isEmpty()) {
      criteria = criteria.and("firstName").matches(firstName);
    }

    if (minAge != null) {
      criteria = criteria.and("age").greaterThanEqual(minAge);
    }

    CriteriaQuery query = new CriteriaQuery(criteria);
    SearchHits<MegaCorp> searchHits = elasticsearchTemplate.search(query, MegaCorp.class);
    
    return searchHits.stream()
        .map(SearchHit::getContent)
        .collect(Collectors.toList());
  }

  /**
   * 动态查询示例2：多条件组合查询
   * 示例URL：/megacorp/dynamic/multi?firstName=Jane&lastName=Smith
   */
  @GetMapping("/dynamic/multi")
  public List<MegaCorp> dynamicMultiSearch(
      @RequestParam(required = false) String firstName,
      @RequestParam(required = false) String lastName) {
    
    Criteria criteria = new Criteria();
    boolean hasCriteria = false;

    // 动态添加多个条件
    if (firstName != null && !firstName.trim().isEmpty()) {
      criteria = criteria.and("firstName").matches(firstName);
      hasCriteria = true;
    }

    if (lastName != null && !lastName.trim().isEmpty()) {
      if (hasCriteria) {
        criteria = criteria.and("lastName").matches(lastName);
      } else {
        criteria = new Criteria("lastName").matches(lastName);
      }
    }

    CriteriaQuery query = new CriteriaQuery(criteria);
    SearchHits<MegaCorp> searchHits = elasticsearchTemplate.search(query, MegaCorp.class);
    
    return searchHits.stream()
        .map(SearchHit::getContent)
        .collect(Collectors.toList());
  }

  /**
   * 动态查询示例3：年龄范围查询
   * 示例URL：/megacorp/dynamic/age?minAge=20&maxAge=40
   */
  @GetMapping("/dynamic/age")
  public List<MegaCorp> dynamicAgeSearch(
      @RequestParam(required = false) Integer minAge,
      @RequestParam(required = false) Integer maxAge) {

    Criteria criteria = new Criteria();
    
    if (minAge != null && maxAge != null) {
      // 年龄范围查询
      criteria = criteria.and("age").between(minAge, maxAge);
    } else if (minAge != null) {
      // 最小年龄查询
      criteria = criteria.and("age").greaterThanEqual(minAge);
    } else if (maxAge != null) {
      // 最大年龄查询
      criteria = criteria.and("age").lessThanEqual(maxAge);
    }

    CriteriaQuery query = new CriteriaQuery(criteria);
    SearchHits<MegaCorp> searchHits = elasticsearchTemplate.search(query, MegaCorp.class);
    
    return searchHits.stream()
        .map(SearchHit::getContent)
        .collect(Collectors.toList());
  }
}
