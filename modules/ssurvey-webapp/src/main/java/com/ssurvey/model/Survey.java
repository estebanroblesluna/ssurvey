package com.ssurvey.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Survey {

  private long id;
  private String name;
  private List<Question> questions;
  private String permalink;
  private String presentation;

  public Survey() {
    this.permalink = UUID.randomUUID().toString();
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

  public String getPermalink() {
    return permalink;
  }

  public void setPermalink(String permalink) {
    this.permalink = permalink;
  }
  
  public String getPresentation() {
    return presentation;
  }

  public void setPresentation(String presentation) {
    this.presentation = presentation;
  }
}
