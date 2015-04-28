package com.ssurvey.model;

import java.util.List;

public class RankQuestion extends Question {

  private List<String> options;

  public RankQuestion() {
    this.setType(this.getType());
  }

  public String getType() {
    return QuestionType.RANK_QUESTION.toString();
  }

  public List<String> getOptions() {
    return options;
  }

  public void setOptions(List<String> options) {
    this.options = options;
  }

  public Answer answer(List<String> answers) {
    return new RankAnswer(this,answers);
  }
}
