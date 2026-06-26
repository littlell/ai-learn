/*
 * 编译：cd jvm/spring-core && mvn compile -q
 * 运行（从 jvm/spring-core 目录执行）：
 *   mvn compile -q exec:java -Dexec.mainClass=com.demo.jvm.spring.core.aop.proxy.AopProxyDemo
 *
 * JDK 17+ 下 --add-opens 不需要，Spring AOP 在 JDK 21 可直接运行。
 */
package com.demo.jvm.spring.core.aop.proxy;

import java.lang.reflect.Proxy;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

// ── 带接口的 Service ──────────────────────────────────────────────────────────

interface GreetingService {
    String greet(String name);
}

class GreetingServiceImpl implements GreetingService {
    @Override
    public String greet(String name) {
        return "Hello, " + name;
    }
}

// ── 不带接口的 Service ────────────────────────────────────────────────────────

class CalculatorService {
    public int add(int a, int b) {
        return a + b;
    }
}

@Aspect
class LogAspect {
    @Around("execution(* com.demo.jvm.spring.core.aop.proxy.GreetingService.*(..))"
        + " || execution(* com.demo.jvm.spring.core.aop.proxy.CalculatorService.*(..))")
    public Object log(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("[Aspect] 前置: " + pjp.getSignature());
        Object result = pjp.proceed();
        System.out.println("[Aspect] 后置: " + pjp.getSignature());
        return result;
    }
}

@Configuration
@EnableAspectJAutoProxy
class AppConfig {
    @Bean
    public static LogAspect logAspect() { return new LogAspect(); }

    @Bean
    public GreetingService greetingService() { return new GreetingServiceImpl(); }

    @Bean
    public CalculatorService calculatorService() { return new CalculatorService(); }
}

public class AopProxyDemo {
    public static void main(String[] args) {
        var ctx = new AnnotationConfigApplicationContext(AppConfig.class);

        GreetingService greeter = ctx.getBean(GreetingService.class);
        CalculatorService calculator = ctx.getBean(CalculatorService.class);

        System.out.println("=== 代理类型检测 ===");
        System.out.println("GreetingService    实现类: " + greeter.getClass().getName());
        System.out.println("  isJDK : " + Proxy.isProxyClass(greeter.getClass()));
        System.out.println("  interfaces: "
            + java.util.Arrays.toString(greeter.getClass().getInterfaces()));

        System.out.println();
        System.out.println("CalculatorService  实现类: " + calculator.getClass().getName());
        System.out.println("  isCGLIB: "
            + (calculator.getClass().getName().contains("$$SpringCGLIB")));
        System.out.println("  superclass: "
            + calculator.getClass().getSuperclass().getName());

        System.out.println("\n=== 业务调用 ===");
        System.out.println(greeter.greet("World"));
        System.out.println("add(3,5) = " + calculator.add(3, 5));

        ctx.close();
    }
}
