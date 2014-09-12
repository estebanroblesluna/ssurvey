package com.ssurvey.model;

import java.util.ArrayList;
import java.util.List;

public class AnsweredSurvey {

  private long id;
  private long surveyId;
  private List<Answer> answers;

  public AnsweredSurvey() {
    this.setAnswers(new ArrayList<Answer>());
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public List<Answer> getAnswers() {
    return answers;
  }

  public void setAnswers(List<Answer> answers) {
    this.answers = answers;
  }

  public long getSurveyId() {
    return surveyId;
  }

  public void setSurveyId(long surveyId) {
    this.surveyId = surveyId;
  }

}
