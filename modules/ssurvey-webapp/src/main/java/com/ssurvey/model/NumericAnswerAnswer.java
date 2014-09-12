package com.ssurvey.model;

public class NumericAnswerAnswer extends Answer {

  private int answer;

  public NumericAnswerAnswer() {
    this.setType(this.getType());
  }

  public int getAnswer() {
    return answer;
  }

  public void setAnswer(int answer) {
    this.answer = answer;
  }

  public String getType() {
    return QuestionType.NUMERIC_ANSWER_QUESTION.toString();
  }
}
