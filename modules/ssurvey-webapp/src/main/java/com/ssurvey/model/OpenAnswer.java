package com.ssurvey.model;

import java.util.List;

public class OpenAnswer extends Answer {

  private String answer;

  public OpenAnswer() {
    this.setType(this.getType());
  }

  public OpenAnswer(Question question, List<String> answers) {
    super(question);
    this.setAnswer(answers.get(0));
    this.setType(this.getType());
  }

  public String getAnswer() {
    return answer;
  }

  public void setAnswer(String answer) {
    this.answer = answer;
  }

  public String getType() {
    return QuestionType.OPEN_QUESTION.toString();
  }
}
