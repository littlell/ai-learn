package com.demo.jvm.spring.boot.elasticsearch;

import org.springframework.stereotype.Service;

import java.util.List;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

/**
 * MegaCorp Service - 优化使用构造函数注入和现代集合初始化
 * 使用 List.of() 替代匿名内部类，使用构造函数注入替代字段注入
 */
@Service
public class MegaCorpService {

  private final MegaCorpRepository megaCorpRepository;

  public MegaCorpService(MegaCorpRepository megaCorpRepository) {
    this.megaCorpRepository = megaCorpRepository;
  }

  @PostConstruct
  public void init() {
    megaCorpRepository.save(new MegaCorp("1", "John", "Smith", 25,
        "I love to go rock climbing",
        List.of("sports", "music")));

    megaCorpRepository.save(new MegaCorp("2", "Jane", "Smith", 32,
        "I like to collect rock albums",
        List.of("music")));

    megaCorpRepository.save(new MegaCorp("3", "Douglas", "Fir", 35,
        "I like to build cabinets",
        List.of("forestry")));
  }

  @PreDestroy
  public void destroy() {
    megaCorpRepository.deleteAll();
  }
}
