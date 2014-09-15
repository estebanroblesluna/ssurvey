package com.ssurvey.model;

import java.util.List;

abstract public class Question {

  private long id;
  private String name;
  @SuppressWarnings("unused")
  private String type;

  public Question() {
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setType(String type) {
    this.type = type;
  }

  abstract public String getType();

  abstract public Answer answer(List<String> answers);

}
