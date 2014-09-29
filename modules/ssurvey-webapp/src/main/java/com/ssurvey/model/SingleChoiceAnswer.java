package com.ssurvey.model;

import java.util.List;

public class SingleChoiceAnswer extends Answer {

  private String answer;

  public SingleChoiceAnswer() {
    this.setType(this.getType());
  }

  public SingleChoiceAnswer(Question question, List<String> answers) {
    super(question);
    this.setType(this.getType());
    this.setAnswer(answers.get(0));
  }

  public String getAnswer() {
    return answer;
  }

  public void setAnswer(String answer) {
    this.answer = answer;
  }

  public String getType() {
    return QuestionType.SINGLE_CHOICE_QUESTION.toString();
  }
}
