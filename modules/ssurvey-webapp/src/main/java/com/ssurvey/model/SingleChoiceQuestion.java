package com.ssurvey.model;

import java.util.List;

public class SingleChoiceQuestion extends Question {

  private List<String> options;

  public SingleChoiceQuestion() {
    this.setType(this.getType());
  }

  public List<String> getOptions() {
    return options;
  }

  public void setOptions(List<String> options) {
    this.options = options;
  }

  public String getType() {
    return QuestionType.SINGLE_CHOICE_QUESTION.toString();
  }

  public Answer answer(List<String> answers) {
    return new SingleChoiceAnswer(this,answers);
  }
}
