package com.ssurvey.model;

import java.util.List;

public class MultipleChoiceAnswer extends Answer {

  private List<String> answers;

  public MultipleChoiceAnswer() {
    this.setType(this.getType());
  }

  public List<String> getAnswers() {
    return answers;
  }

  public void setAnswers(List<String> answers) {
    this.answers = answers;
  }

  public String getType() {
    return QuestionType.MULTIPLE_CHOICE_QUESTION.toString();
  }
}
