package com.demo.jvm.spring.boot.resilience4j;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.stream.IntStream;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ResilientAppControllerTest {
  private final Logger LOGGER = LoggerFactory.getLogger(getClass());

  @Autowired
  private WebApplicationContext webApplicationContext;

  private MockMvc mockMvc;

  @Autowired
  private ExternalAPICaller externalAPICaller;

  @BeforeEach
  public void setUp() {
    this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    externalAPICaller.setRecovered(false);
  }

  @Test
  public void testFailureCircuitBreaker() throws InterruptedException {

    String failureUri = "/api/circuit-breaker/failure";

    // 熔断统计前的请求阈值, 状态码500
    LOGGER.info("熔断统计前的请求阈值, 状态码500");
    IntStream.rangeClosed(1, 5).forEach(i -> {
      try {
        mockMvc.perform(get(failureUri))
            .andExpect(status().isInternalServerError());
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    });

    // 熔断开关打开
    LOGGER.info("熔断开关打开, 状态码503");
    IntStream.rangeClosed(1, 5).forEach(i -> {
      try {
        mockMvc.perform(get(failureUri))
            .andExpect(status().isServiceUnavailable());
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    });

    // 等待6s， 自动切换到半开模式
    LOGGER.info("等待6s， 自动切换到半开模式....");
    Thread.sleep(6000);

    // 半开模式， 允许通过3个请求, 状态码500
    LOGGER.info("半开模式， 允许通过3个请求, 状态码500");
    IntStream.rangeClosed(1, 3).forEach(i -> {
      try {
        mockMvc.perform(get(failureUri))
            .andExpect(status().isInternalServerError());
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    });

    // 3次请求失败， 自动切换到打开状态, 状态码503
    LOGGER.info("3次请求失败， 自动切换到打开状态, 状态码503");
    IntStream.rangeClosed(1, 5).forEach(i -> {
      try {
        mockMvc.perform(get(failureUri))
            .andExpect(status().isServiceUnavailable());
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    });

    // 恢复服务
    LOGGER.info("恢复服务");
    externalAPICaller.setRecovered(true);
    // 等待6s， 自动切换到半开模式
    Thread.sleep(6000);
    LOGGER.info("等待6s， 自动切换到半开模式....");

    // 半开模式， 允许通过3个请求, 状态码200
    LOGGER.info("半开模式， 允许通过3个请求, 状态码200");
    IntStream.rangeClosed(1, 3).forEach(i -> {
      try {
        mockMvc.perform(get(failureUri))
            .andExpect(status().isOk());
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    });

    // 半开模式自动切换到关闭模式, 状态码200
    LOGGER.info("半开模式自动切换到关闭模式, 状态码200");
    IntStream.rangeClosed(1, 10).forEach(i -> {
      try {
        mockMvc.perform(get(failureUri))
            .andExpect(status().isOk());
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    });
  }

  @Test
  public void testSlownessCircuitBreaker() throws InterruptedException {

    String slownessUri = "/api/circuit-breaker/slowness";

    // 熔断统计前的请求阈值, 状态码200
    LOGGER.info("熔断统计前的请求阈值, 状态码200");
    IntStream.rangeClosed(1, 5).forEach(i -> {
      try {
        mockMvc.perform(get(slownessUri))
            .andExpect(status().isOk());
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    });

    // 熔断开关打开
    LOGGER.info("熔断开关打开, 状态码503");
    IntStream.rangeClosed(1, 5).forEach(i -> {
      try {
        mockMvc.perform(get(slownessUri))
            .andExpect(status().isServiceUnavailable());
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    });

    // 等待6s， 自动切换到半开模式
    LOGGER.info("等待6s， 自动切换到半开模式....");
    Thread.sleep(6000);

    // 半开模式， 允许通过3个请求, 状态码500
    LOGGER.info("半开模式， 允许通过3个请求, 状态码500");
    IntStream.rangeClosed(1, 3).forEach(i -> {
      try {
        mockMvc.perform(get(slownessUri))
            .andExpect(status().isOk());
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    });

    // 3次请求失败， 自动切换到打开状态, 状态码503
    LOGGER.info("3次请求失败， 自动切换到打开状态, 状态码503");
    IntStream.rangeClosed(1, 5).forEach(i -> {
      try {
        mockMvc.perform(get(slownessUri))
            .andExpect(status().isServiceUnavailable());
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    });

    // 恢复服务
    LOGGER.info("恢复服务");
    externalAPICaller.setRecovered(true);
    // 等待6s， 自动切换到半开模式
    Thread.sleep(6000);
    LOGGER.info("等待6s， 自动切换到半开模式....");

    // 半开模式， 允许通过3个请求, 状态码200
    LOGGER.info("半开模式， 允许通过3个请求, 状态码200");
    IntStream.rangeClosed(1, 3).forEach(i -> {
      try {
        mockMvc.perform(get(slownessUri))
            .andExpect(status().isOk());
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    });

    // 半开模式自动切换到关闭模式, 状态码200
    LOGGER.info("半开模式自动切换到关闭模式, 状态码200");
    IntStream.rangeClosed(1, 10).forEach(i -> {
      try {
        mockMvc.perform(get(slownessUri))
            .andExpect(status().isOk());
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    });
  }
}