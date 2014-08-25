package com.ssurvey.model;

public class RankAnswerQuestion extends Question {

  private long upperBound;

  public RankAnswerQuestion() {
    this.setType(this.getType());
  }

  public long getUpperBound() {
    return upperBound;
  }

  public void setUpperBound(long upperBound) {
    this.upperBound = upperBound;
  }

  public String getType() {
    return QuestionType.RANK_ANSWER_QUESTION.toString();
  }
}
