package com.redhat.dto;

public class Login {
  public String user;
  public String pass;
  public Login(String user, String pass) {
    this.user = user;
    this.pass = pass;
  }
  public Login(){}
}
