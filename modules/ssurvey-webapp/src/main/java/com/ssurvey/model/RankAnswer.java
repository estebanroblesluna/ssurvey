package com.ssurvey.model;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class RankAnswer extends Answer {

  private List<String> answers;

  public RankAnswer() {
    this.setType(this.getType());
  }

  public RankAnswer(Question question, List<String> answers) {
    super(question);
    answers = new LinkedList<String>(Arrays.asList(answers.get(0).split("[|]")));
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
    return QuestionType.RANK_QUESTION.toString();
  }
}
