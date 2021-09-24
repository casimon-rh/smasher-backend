package com.redhat;

public class Pod {
  public String name;
  public boolean isRunning;
  public String runtime;
  public Pod(String name, boolean isRunning, String runtime) {
    this.name = name;
    this.isRunning = isRunning;
    this.runtime = runtime;
  }
}
