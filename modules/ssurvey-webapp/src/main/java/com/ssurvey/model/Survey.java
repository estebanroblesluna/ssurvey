package com.ssurvey.model;

import java.util.ArrayList;
import java.util.List;

public class Survey {

  private long id;
  private String name;
  private List<Question> questions;

  public Survey() {
    this.questions = new ArrayList<Question>();
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

  public List<Question> getQuestions() {
    return questions;
  }

  public void setQuestions(List<Question> questions) {
    this.questions = questions;
  }
}
