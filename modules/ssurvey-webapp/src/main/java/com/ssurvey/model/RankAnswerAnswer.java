package com.ssurvey.model;

import java.util.List;

public class RankAnswerAnswer extends Answer {

  private List<String> answers;

  public RankAnswerAnswer() {
    this.setType(this.getType());
  }

  public RankAnswerAnswer(List<String> answers) {
    this.setType(this.getType());
    this.setAnswers(answers);
  }

  public List<String> getAnswers() {
    return answers;
  }

  public void setAnswers(List<String> answers) {
    this.answers = answers;
  }

  public String getType() {
    return QuestionType.RANK_ANSWER_QUESTION.toString();
  }
}
