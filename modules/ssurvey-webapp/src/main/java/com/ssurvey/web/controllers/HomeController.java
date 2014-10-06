package com.ssurvey.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ssurvey.service.SurveyService;
import com.ssurvey.service.signin.SSurveyUser;

@Controller
public class HomeController extends SSurveyGenericController {

  @Autowired
  private SurveyService surveyService;

  @RequestMapping(value = "/", method = RequestMethod.GET)
  public ModelAndView showLogin() {
    if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() != "anonymousUser") {
      ModelAndView mv = new ModelAndView("redirect:/surveys/");
      return mv;
    } else {
      ModelAndView mv = new ModelAndView("login");
      return mv;
    }
  }
}
