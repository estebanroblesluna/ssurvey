package com.ssurvey.service;

import com.ssurvey.model.Question;
import com.ssurvey.model.Survey;
import com.ssurvey.repositories.SurveyRepository;

public class SurveyService {

  private SurveyRepository surveyRepository;

  public SurveyService() {
  }

  public SurveyRepository getSurveyRepository() {
    return surveyRepository;
  }

  public void setSurveyRepository(SurveyRepository surveyRepository) {
    this.surveyRepository = surveyRepository;
  }

  public void saveSurvey(Survey survey) {
    this.surveyRepository.saveSurvey(survey);
  }

  public void saveQuestion(Question question) {
    this.surveyRepository.saveQuestion(question);
  }

}
