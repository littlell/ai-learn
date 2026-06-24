/*
 * 编译：cd jvm/spring-core && mvn compile -q
 * 运行（从 jvm/spring-core 目录执行）：
 *   mvn compile -q exec:java -Dexec.mainClass=com.demo.jvm.spring.core.lifecycle.bpp.BppLifecycleDemo
 */
package com.demo.jvm.spring.core.lifecycle.bpp;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 演示 BeanPostProcessor 在 Bean 生命周期中的两个卡点。
 *
 * 完整顺序（每个非 BPP 的 Bean 都走这条链）：
 *   1. 构造函数
 *   2. BeanNameAware.setBeanName          — Aware 回调
 *   3a. BPP#1.postProcessBeforeInitialization
 *   3b. BPP#2.postProcessBeforeInitialization
 *   4. @PostConstruct                     — CommonAnnotationBeanPostProcessor 触发
 *   5. InitializingBean.afterPropertiesSet
 *   6a. BPP#1.postProcessAfterInitialization
 *   6b. BPP#2.postProcessAfterInitialization  ← AOP 代理在这里替换原始对象
 *   7. 容器就绪，业务代码拿到的是步骤 6 返回的对象
 *   8. @PreDestroy（容器关闭时）
 */
public class BppLifecycleDemo {

    // ── 目标 Bean ──────────────────────────────────────────────────────────

    static class ServiceBean implements BeanNameAware, InitializingBean {

        private String beanName;

        public ServiceBean() {
            System.out.println("[ServiceBean]  1. 构造函数");
        }

        @Override
        public void setBeanName(String name) {
            this.beanName = name;
            System.out.println("[ServiceBean]  2. BeanNameAware.setBeanName → " + name);
        }

        @PostConstruct
        public void postConstruct() {
            System.out.println("[ServiceBean]  4. @PostConstruct");
        }

        @Override
        public void afterPropertiesSet() {
            System.out.println("[ServiceBean]  5. InitializingBean.afterPropertiesSet");
        }

        @PreDestroy
        public void preDestroy() {
            System.out.println("[ServiceBean]  8. @PreDestroy");
        }

        public void doWork() {
            System.out.println("[ServiceBean]  doWork (beanName=" + beanName + ")");
        }
    }

    // ── BPP #1：只打日志，不替换对象 ───────────────────────────────────────

    static class LoggingBpp implements BeanPostProcessor {

        @Override
        public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
            if (bean instanceof ServiceBean) {
                System.out.println("[LoggingBpp]   3a. Before-Init  → " + beanName);
            }
            return bean;
        }

        @Override
        public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
            if (bean instanceof ServiceBean) {
                System.out.println("[LoggingBpp]   6a. After-Init   → " + beanName);
            }
            return bean;
        }
    }

    // ── BPP #2：After-Init 阶段用包装对象替换原始 Bean（模拟 AOP 代理） ────

    static class ProxyBpp implements BeanPostProcessor {

        @Override
        public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
            if (bean instanceof ServiceBean) {
                System.out.println("[ProxyBpp]     3b. Before-Init  → " + beanName);
            }
            return bean;
        }

        @Override
        public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
            if (bean instanceof ServiceBean original) {
                System.out.println("[ProxyBpp]     6b. After-Init   → 返回 Proxy 替换原始对象");
                // 返回新对象，Spring 把容器里的引用换掉；这正是 AOP 代理的工作方式
                return new ServiceBean() {
                    @Override
                    public void doWork() {
                        System.out.println("[Proxy]        前置增强");
                        original.doWork();
                        System.out.println("[Proxy]        后置增强");
                    }
                };
            }
            return bean;
        }
    }

    // ── Configuration ──────────────────────────────────────────────────────

    @Configuration
    static class AppConfig {

        // BPP 的 @Bean 必须是 static：让容器在实例化 Config 之前就注册 BPP，
        // 否则 Spring 会警告 Config Bean 本身无法被所有 BPP 处理（鸡生蛋问题）
        @Bean
        public static LoggingBpp loggingBpp() { return new LoggingBpp(); }

        @Bean
        public static ProxyBpp proxyBpp() { return new ProxyBpp(); }

        @Bean
        public ServiceBean serviceBean() { return new ServiceBean(); }
    }

    // ── Main ───────────────────────────────────────────────────────────────

    public static void main(String[] args) {
        System.out.println("=== 容器启动 ===");
        var ctx = new AnnotationConfigApplicationContext(AppConfig.class);

        System.out.println("\n=== 获取 Bean 并调用 doWork ===");
        // 注意：getBean 拿到的是 ProxyBpp 替换后的对象
        ServiceBean bean = ctx.getBean(ServiceBean.class);
        bean.doWork();

        System.out.println("\n=== 容器关闭 ===");
        ctx.close();
    }
}
