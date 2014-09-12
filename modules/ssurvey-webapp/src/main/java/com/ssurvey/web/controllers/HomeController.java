package com.ssurvey.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ssurvey.service.SurveyService;

@Controller
public class HomeController extends SSurveyGenericController {

  @Autowired
  private SurveyService surveyService;

  @RequestMapping(value = "/", method = RequestMethod.GET)
  public ModelAndView showLogin() {
    ModelAndView mv = new ModelAndView("login");
    return mv;
  }

}
