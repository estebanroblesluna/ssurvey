package com.ssurvey.model;

import java.util.ArrayList;
import java.util.List;

public class Survey {

  private long id;
  private String name;
  private List<Question> questions;
  private long permalink;

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

  public long getPermalink() {
    this.permalink = id ^ (id << 40);
    return permalink;
  }

  public void setPermalink(long permalink) {
    this.permalink = permalink;
  }
}
