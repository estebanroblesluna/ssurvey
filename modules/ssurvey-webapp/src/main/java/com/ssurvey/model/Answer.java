package com.ssurvey.model;

abstract public class Answer {

  private long id;
  @SuppressWarnings("unused")
  private String type;

  public Answer() {
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public void setType(String type) {
    this.type = type;
  }

  abstract public String getType();
}
