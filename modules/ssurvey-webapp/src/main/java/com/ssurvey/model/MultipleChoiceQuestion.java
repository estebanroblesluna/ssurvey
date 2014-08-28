package com.ssurvey.model;

import java.util.List;

public class MultipleChoiceQuestion extends Question {

  private List<String> options;
  private long upperBound;

  public MultipleChoiceQuestion() {
    this.setType(this.getType());
  }

  public List<String> getOptions() {
    return options;
  }

  public void setOptions(List<String> options) {
    this.options = options;
    this.upperBound = options.size();
  }

  public long getUpperBound() {
    return upperBound;
  }

  public void setUpperBound(long upperBound) {
    this.upperBound = upperBound;
  }

  public String getType() {
    return QuestionType.MULTIPLE_CHOICE_QUESTION.toString();
  }
}
