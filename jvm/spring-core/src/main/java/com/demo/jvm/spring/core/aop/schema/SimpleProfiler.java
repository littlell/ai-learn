package com.demo.jvm.spring.core.aop.schema;


import org.aspectj.lang.ProceedingJoinPoint;

public class SimpleProfiler {

  public void profilerBefore() throws Throwable {
    System.out.println("profiler before");
  }

  public void profilerAfter() throws Throwable {
    System.out.println("profiler after");
  }

  public void profilerAfterReturning() throws Throwable {
    System.out.println("profiler after returning");
  }

  public void profilerException() throws Throwable {
    System.out.println("profiler exception");
  }

  public Object profilerAround(ProceedingJoinPoint joinPoint) throws Throwable {
    System.out.println("profiler around start");
    Object obj = joinPoint.proceed();
    System.out.println("profiler around end");
    return obj;
  }
}
