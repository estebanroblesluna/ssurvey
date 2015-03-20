package com.ssurvey.web.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
  public ModelAndView renderSurvey(@PathVariable(value = "permalink") String permalink, HttpServletRequest request) {
    Survey survey = this.surveyService.getSurveyByPermalink(permalink);
    if (survey == null) {
      ModelAndView mv = new ModelAndView("error");
      mv.addObject("errorMessage", "The page you're looking for doesn't exist.");
      return mv;
    } else if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() == "anonymousUser") {
      ModelAndView mv = new ModelAndView("redirect:/?url=" + request.getRequestURI());
      return mv;
    } else {
      Account account = this.getLoggedUser();
      if (this.answerService.userHasAnsweredSurvey(account.getId(), survey.getId())) {
        ModelAndView mv = this.createModelAndView("answeredSurvey");
        mv.addObject("survey", survey);
        return mv;
      } else {
        ModelAndView mv = this.createModelAndView("survey");
        mv.addObject("survey", survey);
        return mv;
      }
    }
  }

  @RequestMapping(value = "/", method = RequestMethod.GET)
  public ModelAndView showRecommendedSurveys() {
    if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() == "anonymousUser") {
      ModelAndView mv = new ModelAndView("redirect:/");
      return mv;
    } else {
      ModelAndView mv = this.createModelAndView("recommended-surveys");
      List<Survey> surveys = this.surveyService.getSurveys();
      mv.addObject("surveys", surveys);
      return mv;
    }
  }

  @RequestMapping(value = "/{permalink}", method = RequestMethod.POST)
  public String submitAnsweredSurvey(@PathVariable(value = "permalink") String permalink, @RequestParam MultiValueMap<String, String> params) {
    if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() == "anonymousUser") {
      return "redirect:/";
    } else {
      if (this.answerService.answer(this.getLoggedUser().getId(), permalink, params)) {
    	//this.linkedInInformationService.shareSurvey("http://www.google.com", this.getLoggedUser());
    	return "redirect:/surveys/" + permalink;
      } else {
        return "redirect:/errorPage/";
      }
    }
  }
}
