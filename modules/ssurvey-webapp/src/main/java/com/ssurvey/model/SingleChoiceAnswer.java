package com.ssurvey.model;

public class SingleChoiceAnswer extends Answer {

  private String answer;

  public SingleChoiceAnswer() {
    this.setType(this.getType());
  }

  public String getAnswer() {
    return answer;
  }

  public void setAnswers(String answer) {
    this.answer = answer;
  }

  public String getType() {
    return QuestionType.SINGLE_CHOICE_QUESTION.toString();
  }
}
