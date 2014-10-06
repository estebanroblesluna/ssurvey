package com.ssurvey.web.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ssurvey.model.Account;
import com.ssurvey.model.Survey;
import com.ssurvey.service.AnswerService;
import com.ssurvey.service.QuestionService;
import com.ssurvey.service.LinkedInInformationService;
import com.ssurvey.service.SurveyService;
import com.ssurvey.service.signin.SSurveyUser;

@Controller
@RequestMapping("/surveys/")
public class SurveyController extends SSurveyGenericController {

  @Autowired
  private SurveyService surveyService;
  @Autowired
  private QuestionService questionService;
  @Autowired
  private LinkedInInformationService linkedInInformationService;
  @Autowired
  private AnswerService answerService;

  @RequestMapping(value = "/{permalink}", method = RequestMethod.GET)
  public ModelAndView renderSurvey(@PathVariable(value = "permalink") Long permalink) {
    Account account = ((SSurveyUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getAccount();
    Survey survey = this.surveyService.getSurveyByPermalink(permalink);
    if (this.answerService.userHasAnsweredSurvey(account.getId(), survey.getId())) {
      ModelAndView mv = this.createModelAndView("error");
      return mv;
    } else {
      ModelAndView mv = this.createModelAndView("survey");
      mv.addObject("survey", survey);
      return mv;
    }
  }

  @RequestMapping(value = "/", method = RequestMethod.GET)
  public ModelAndView showRecommendedSurveys() {
    ModelAndView mv = this.createModelAndView("recommended-surveys");
    List<Survey> surveys = this.surveyService.getSurveys();
    mv.addObject("surveys", surveys);
    return mv;
  }

  @RequestMapping(value = "/{permalink}", method = RequestMethod.POST)
  public String submitAnsweredSurvey(@PathVariable(value = "permalink") Long permalink, @RequestParam MultiValueMap<String, String> params) {
    this.answerService.answer(permalink, params);
    return "redirect:/surveys/";
  }
}
