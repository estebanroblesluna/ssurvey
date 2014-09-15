package com.ssurvey.model;

import java.util.List;

public class NumericAnswerQuestion extends Question {

  private long upperBound;
  private long lowerBound;

  public NumericAnswerQuestion() {
    this.setType(this.getType());
  }

  public long getUpperBound() {
    return upperBound;
  }

  public void setUpperBound(long upperBound) {
    this.upperBound = upperBound;
  }

  public long getLowerBound() {
    return lowerBound;
  }

  public void setLowerBound(long lowerBound) {
    this.lowerBound = lowerBound;
  }

  public String getType() {
    return QuestionType.NUMERIC_ANSWER_QUESTION.toString();
  }

  public Answer answer(List<String> answers) {
    return new NumericAnswerAnswer(answers);
  }
}
