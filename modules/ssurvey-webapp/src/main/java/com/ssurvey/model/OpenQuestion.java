package com.ssurvey.model;

import java.util.List;

public class OpenQuestion extends Question {

  private long upperBound;

  public OpenQuestion() {
    this.setType(this.getType());
  }

  public long getUpperBound() {
    return upperBound;
  }

  public void setUpperBound(long upperBound) {
    this.upperBound = upperBound;
  }

  public String getType() {
    return QuestionType.OPEN_QUESTION.toString();
  }

  public Answer answer(List<String> answers) {
    return new OpenAnswer(this,answers);
  }
}
