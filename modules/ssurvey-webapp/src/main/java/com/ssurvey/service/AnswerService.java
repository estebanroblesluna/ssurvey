package com.ssurvey.service;

import java.util.List;

import org.jsoup.helper.Validate;
import org.springframework.transaction.annotation.Transactional;

import com.ssurvey.model.AnsweredSurvey;
import com.ssurvey.model.LinkedInUserProfile;
import com.ssurvey.model.Survey;
import com.ssurvey.repositories.AnswerRepository;

public class AnswerService {

  private AnswerRepository answerRepository;
  private LinkedInInformationService linkedInInformationService;
  private SurveyService surveyService;

  public AnswerService(AnswerRepository answerRepository, LinkedInInformationService linkedInInformationService, SurveyService surveyService) {
    Validate.notNull(answerRepository);
    Validate.notNull(linkedInInformationService);
    Validate.notNull(surveyService);

    this.answerRepository = answerRepository;
    this.linkedInInformationService = linkedInInformationService;
    this.surveyService = surveyService;
  }

  @Transactional
  public void saveAnsweredSurvey(AnsweredSurvey answeredSurvey) {
    this.answerRepository.saveAnsweredSurvey(answeredSurvey);
  }

  @Transactional
  public List<AnsweredSurvey> getAnsweredSurveysBySurveyId(long surveyId) {
    return this.answerRepository.getAnsweredSurveysBySurveyId(surveyId);
  }

  @Transactional
  public void answer(long surveyId) {
    Survey survey = this.surveyService.getSurveyById(surveyId);
    LinkedInUserProfile linkedInProfile = this.linkedInInformationService.getRespondentInformation();
    AnsweredSurvey answeredSurvey = new AnsweredSurvey();
    answeredSurvey.setSurveyId(surveyId);
    answeredSurvey.setLinkedInUserProfile(linkedInProfile);
    this.saveAnsweredSurvey(answeredSurvey);
  }

  public boolean userHasAnsweredSurvey(long linkedInUserId, long surveyId) {
    for (AnsweredSurvey answeredSurvey : this.getAnsweredSurveysBySurveyId(surveyId)) {
      if (answeredSurvey.getLinkedInUserProfile().getId().equals(Long.toString(linkedInUserId))) {
        return true;
      }
    }
    return false;
  }

}
