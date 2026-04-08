package com.demo.jvm.spring.boot.cassandra.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.config.SchemaAction;
import org.springframework.data.cassandra.core.cql.keyspace.CreateKeyspaceSpecification;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

import java.util.Arrays;
import java.util.List;

/**
 * Cassandra 配置类 - 简单示例
 */
@Configuration
@EnableCassandraRepositories(basePackages = "com.demo.jvm.spring.boot.cassandra.repository")
public class CassandraConfig extends AbstractCassandraConfiguration {

  @Value("${spring.data.cassandra.keyspace-name:spring_cassandra}")
  private String keyspaceName;

  @Value("${spring.data.cassandra.local-datacenter:datacenter1}")
  private String localDatacenter;

  @Override
  protected String getKeyspaceName() {
    return keyspaceName;
  }

  @Override
  protected String getLocalDataCenter() {
    return localDatacenter;
  }

  @Override
  public SchemaAction getSchemaAction() {
    return SchemaAction.CREATE_IF_NOT_EXISTS;
  }

  @Override
  public String[] getEntityBasePackages() {
    return new String[]{"com.demo.jvm.spring.boot.cassandra.entity"};
  }

  /**
   * 自动创建 keyspace
   */
  @Override
  protected List<CreateKeyspaceSpecification> getKeyspaceCreations() {
    CreateKeyspaceSpecification specification = CreateKeyspaceSpecification
        .createKeyspace(keyspaceName)
        .ifNotExists()
        .withSimpleReplication(1);
    
    return Arrays.asList(specification);
  }
}
