package com.ssurvey.model;

abstract public class Answer {

  private long id;
  private Question question;
  @SuppressWarnings("unused")
  private String type;

  public Answer() {
  }

  public Answer(Question question) {
    this.setQuestion(question);
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

  public Question getQuestion() {
    return question;
  }

  public void setQuestion(Question question) {
    this.question = question;
  }
}
