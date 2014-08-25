package com.ssurvey.model;

public class OpenAnswerQuestion extends Question {

  private long upperBound;

  public OpenAnswerQuestion() {
    this.setType(this.getType());
  }

  public long getUpperBound() {
    return upperBound;
  }

  public void setUpperBound(long upperBound) {
    this.upperBound = upperBound;
  }

  public String getType() {
    return QuestionType.OPEN_ANSWER_QUESTION.toString();
  }
}
