package com.ssurvey.model;

import java.util.List;

public class RankAnswerQuestion extends Question {

  private List<String> options;

  public RankAnswerQuestion() {
    this.setType(this.getType());
  }

  public String getType() {
    return QuestionType.RANK_ANSWER_QUESTION.toString();
  }

  public List<String> getOptions() {
    return options;
  }

  public void setOptions(List<String> options) {
    this.options = options;
  }

}
