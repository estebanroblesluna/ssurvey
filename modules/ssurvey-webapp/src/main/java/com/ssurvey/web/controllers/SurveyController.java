package com.ssurvey.web.controllers;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ssurvey.model.Answer;
import com.ssurvey.model.AnsweredSurvey;
import com.ssurvey.model.Question;
import com.ssurvey.model.QuestionType;
import com.ssurvey.model.Survey;
import com.ssurvey.service.AnswerService;
import com.ssurvey.service.QuestionService;
import com.ssurvey.service.LinkedInInformationService;
import com.ssurvey.service.SurveyService;

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

  @RequestMapping(value = "/{surveyId}", method = RequestMethod.GET)
  public ModelAndView renderSurvey(@PathVariable(value = "surveyId") Long surveyId) {
    ModelAndView mv = this.createModelAndView("survey");
    Survey survey = this.surveyService.getSurveyById(surveyId);
    mv.addObject("survey", survey);
    return mv;
  }

  @RequestMapping(value = "/", method = RequestMethod.GET)
  public ModelAndView showRecommendedSurveys() {
    ModelAndView mv = this.createModelAndView("recommended-surveys");
    List<Survey> surveys = this.surveyService.getSurveys();
    mv.addObject("surveys", surveys);
    return mv;
  }

  @RequestMapping(value = "/{surveyId}", method = RequestMethod.POST)
  public String submitAnsweredSurvey(@PathVariable(value = "surveyId") Long surveyId, @RequestParam MultiValueMap<String, String> params) {
    this.answerService.answer(this.getLoggedUser().getId(),surveyId, params);
    return "redirect:/surveys/";
  }
}
