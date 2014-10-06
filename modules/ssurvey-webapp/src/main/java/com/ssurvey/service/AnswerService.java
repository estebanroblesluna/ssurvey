package com.ssurvey.service;

import org.jsoup.helper.Validate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.MultiValueMap;

import com.ssurvey.model.Answer;
import com.ssurvey.model.AnsweredSurvey;
import com.ssurvey.model.GetRespondentInformationTicket;
import com.ssurvey.model.LinkedInUserProfile;
import com.ssurvey.model.Question;
import com.ssurvey.model.Survey;
import com.ssurvey.repositories.AnswerRepository;

public class AnswerService {

  private AnswerRepository answerRepository;
  private LinkedInInformationService linkedInInformationService;
  private SurveyService surveyService;
  private QuestionService questionService;
  private TicketService ticketService;

  public AnswerService(AnswerRepository answerRepository, LinkedInInformationService linkedInInformationService, SurveyService surveyService,
          QuestionService questionService, TicketService ticketService) {
    Validate.notNull(answerRepository);
    Validate.notNull(linkedInInformationService);
    Validate.notNull(surveyService);
    Validate.notNull(questionService);
    Validate.notNull(ticketService);

    this.answerRepository = answerRepository;
    this.linkedInInformationService = linkedInInformationService;
    this.surveyService = surveyService;
    this.questionService = questionService;
    this.ticketService = ticketService;
  }

  @Transactional
  public void saveAnsweredSurvey(AnsweredSurvey answeredSurvey) {
    this.answerRepository.saveAnsweredSurvey(answeredSurvey);
  }
  
  @Transactional
  public void setAnswerLinkedInProfile(AnsweredSurvey answeredSurvey, LinkedInUserProfile profile){
    profile = this.linkedInInformationService.getLinkedInUserProfile(profile.getId());
    answeredSurvey = this.answerRepository.getAnsweredSurveyById(answeredSurvey.getId());
    answeredSurvey.setLinkedInUserProfile(profile);
    this.answerRepository.saveAnsweredSurvey(answeredSurvey);
  }

  @Transactional
  public void answer(long userId, Long surveyId, MultiValueMap<String, String> params){
    Survey survey = this.surveyService.getSurveyByPermalink(surveyId);
    AnsweredSurvey answeredSurvey = new AnsweredSurvey();
    answeredSurvey.setSurvey(survey);

    for (String s : params.keySet()) {
      Long questionId = Long.parseLong(s.split("_")[1]);
      Question question = questionService.getQuestion(questionId);
      Answer answer = question.answer(params.get(s));
      answeredSurvey.addAnswer(answer);
    }

    this.saveAnsweredSurvey(answeredSurvey);
    this.ticketService.saveTicket(new GetRespondentInformationTicket(answeredSurvey,userId));
  }

}
