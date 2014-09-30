package com.ssurvey.service;

import java.util.List;

import org.jsoup.helper.Validate;
import org.springframework.transaction.annotation.Transactional;

import com.ssurvey.model.Question;
import com.ssurvey.model.Survey;
import com.ssurvey.repositories.SurveyRepository;

public class SurveyService {

  private SurveyRepository surveyRepository;
  private QuestionService questionService;

  public SurveyService() {
  }

  public SurveyService(SurveyRepository surveyRepository, QuestionService questionService) {
    Validate.notNull(surveyRepository);
    Validate.notNull(questionService);

    this.surveyRepository = surveyRepository;
    this.questionService = questionService;
  }

  @Transactional
  public List<Survey> getSurveys() {
    return this.surveyRepository.getSurveys();
  }

  @Transactional
  public Survey getSurveyById(long id) {
    return this.surveyRepository.getSurveyById(id);
  }

  @Transactional
  public void saveSurvey(Survey survey) {
    this.surveyRepository.saveSurvey(survey);
    for (Question question : survey.getQuestions()) {
      this.questionService.saveQuestion(question);
    }
  }

}
