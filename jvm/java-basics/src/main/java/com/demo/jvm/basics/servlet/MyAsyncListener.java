package com.demo.jvm.basics.servlet;

import java.io.IOException;
import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;

/** Created by xialei on 16/5/30. */
public class MyAsyncListener implements AsyncListener {
  private LoggerFactory.Logger logger = LoggerFactory.getNormalLogger();

  public void onComplete(AsyncEvent asyncEvent) throws IOException {
    logger.write("MyAsyncListener's onComplete()..");
  }

  public void onTimeout(AsyncEvent asyncEvent) throws IOException {
    logger.write("MyAsyncListener's onTimeout()..");
  }

  public void onError(AsyncEvent asyncEvent) throws IOException {
    logger.write("MyAsyncListener's onError()..");
  }

  public void onStartAsync(AsyncEvent asyncEvent) throws IOException {
    logger.write("MyAsyncListener's onStartAsync()..");
  }
}
