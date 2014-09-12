package com.ssurvey.service;

import org.springframework.transaction.annotation.Transactional;

import com.ssurvey.model.AnsweredSurvey;
import com.ssurvey.repositories.AnswerRepository;

public class AnswerService {

  private AnswerRepository answerRepository;

  public AnswerService() {
  }

  public AnswerRepository getAnswerRepository() {
    return answerRepository;
  }

  public void setAnswerRepository(AnswerRepository answerRepository) {
    this.answerRepository = answerRepository;
  }

  @Transactional
  public void saveAnsweredSurvey(AnsweredSurvey answeredSurvey) {
    this.answerRepository.saveAnsweredSurvey(answeredSurvey);
  }

  public AnsweredSurvey getAnsweredSurveyById(long id) {
    return this.answerRepository.getAnsweredSurveyById(id);
  }

  public AnsweredSurvey getAnsweredSurveyBySurveyId(long id) {
    return this.answerRepository.getAnsweredSurveyBySurveyId(id);
  }
}
