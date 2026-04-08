#### Spring Boot Demo（按业务主题拆分）

| 模块 | 内容 |
|------|------|
| demo-spring-boot-config | 配置、JDBC、Web、Cache、Actuator、多环境 |
| demo-spring-boot-elasticsearch | spring-boot-starter-data-elasticsearch |
| demo-spring-boot-rabbitmq | spring-boot-starter-amqp（Direct/Fanout/Topic） |
| demo-spring-boot-cassandra | spring-boot-starter-data-cassandra |
| demo-spring-boot-mongodb | spring-boot-starter-data-mongodb |
| demo-spring-boot-multicache | 多缓存（Caffeine、EhCache、Hazelcast、Redis） |
| demo-spring-boot-kafka | spring-kafka |
| demo-spring-boot-rsocket | spring-boot-starter-rsocket |
| demo-spring-boot-resilience4j | resilience4j 熔断/限流等 |
| demo-spring-boot-errorhandling | 全局错误处理、Gateway 等 |

包名已与主题对齐：`com.demo.jvm.spring.boot.config`、`com.demo.jvm.spring.boot.elasticsearch`、`com.demo.jvm.spring.boot.kafka` 等。在 `spring-boot` 目录下执行 `mvn compile` 可编译全部模块。
