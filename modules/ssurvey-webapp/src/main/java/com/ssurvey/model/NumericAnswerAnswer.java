package com.ssurvey.model;

import java.util.List;

public class NumericAnswerAnswer extends Answer {

  private int answer;

  public NumericAnswerAnswer() {
    this.setType(this.getType());
  }

  public NumericAnswerAnswer(Question question, List<String> answers) {
    super(question);
    this.setType(this.getType());
    this.answer = Integer.parseInt(answers.get(0));
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
