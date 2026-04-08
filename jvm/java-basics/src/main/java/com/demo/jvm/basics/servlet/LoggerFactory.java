package com.demo.jvm.basics.servlet;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by xialei on 16/5/30.
 */
public class LoggerFactory {

  private static Logger initLogger;

  private static Logger destroyLogger;

  private static Logger normalLogger;

  /**
   * constructor.
   */
  public static Logger getInitLogger() {
    if (initLogger == null) {
      initLogger = new Logger("/tmp/init.log");
    }
    return initLogger;
  }

  /**
   * return destroy logger.
   * @return destroy logger.
   */
  public static Logger getDestroyLogger() {
    if (destroyLogger == null) {
      destroyLogger = new Logger("/tmp/destroy.log");
    }
    return destroyLogger;
  }

  /**
   * return normal logger.
   * @return normal logger.
   */
  public static Logger getNormalLogger() {
    if (normalLogger == null) {
      normalLogger = new Logger("/tmp/servlet.log");
    }
    return normalLogger;
  }

  static class Logger {

    private String loggerFile;

    public Logger(String loggerFile) {
      this.loggerFile = loggerFile;
      try {
        File f = new File(loggerFile);
        if (!f.exists()) {
          f.createNewFile();
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    public void write(String s) {
      File f = new File(loggerFile);
      FileWriter fw;
      try {
        fw = new FileWriter(f);
        PrintWriter pw = new PrintWriter(fw);
        pw.println(s);
        fw.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}
