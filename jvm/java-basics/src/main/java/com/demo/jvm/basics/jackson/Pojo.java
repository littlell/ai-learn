package com.demo.jvm.basics.jackson;

public class Pojo {
  private Boolean liveness;
  private Boolean readiness;

  public Boolean getLiveness() {
    return liveness;
  }

  public void setLiveness(Boolean liveness) {
    this.liveness = liveness;
  }

  public Boolean getReadiness() {
    return readiness;
  }

  public void setReadiness(Boolean readiness) {
    this.readiness = readiness;
  }

  @Override
  public String toString() {
    return "Pojo{" +
        "liveness=" + liveness +
        ", readiness=" + readiness +
        '}';
  }
}
