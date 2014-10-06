package com.ssurvey.model;

import java.util.ArrayList;
import java.util.List;

public class AnsweredSurvey {

  private long id;
  private Survey survey;
  private LinkedInUserProfile linkedInUserProfile;
  private long accountId;
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

  public void addAnswer(Answer answer) {
    this.answers.add(answer);
  }

  public LinkedInUserProfile getLinkedInUserProfile() {
    return linkedInUserProfile;
  }

  public void setLinkedInUserProfile(LinkedInUserProfile linkedInUserProfile) {
    this.linkedInUserProfile = linkedInUserProfile;
  }

  public Survey getSurvey() {
    return survey;
  }

  public void setSurvey(Survey survey) {
    this.survey = survey;
  }

  public long getAccountId() {
    return accountId;
  }

  public void setAccountId(long accountId) {
    this.accountId = accountId;
  }
}
