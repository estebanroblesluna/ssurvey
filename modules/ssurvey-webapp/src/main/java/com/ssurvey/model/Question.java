package com.ssurvey.model;

public class Question {

  private long id;
  private String name;

  public Question() {
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getTitle() {
    return name;
  }

  public void setTitle(String title) {
    this.name = title;
  }
}
