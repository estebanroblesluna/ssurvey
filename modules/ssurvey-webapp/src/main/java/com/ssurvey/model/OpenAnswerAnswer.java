package com.ssurvey.model;

public class OpenAnswerAnswer extends Answer {

  private String answer;

  public OpenAnswerAnswer() {
    this.setType(this.getType());
  }

  public String getAnswer() {
    return answer;
  }

  public void setAnswer(String answer) {
    this.answer = answer;
  }

  public String getType() {
    return QuestionType.OPEN_ANSWER_QUESTION.toString();
  }
}
