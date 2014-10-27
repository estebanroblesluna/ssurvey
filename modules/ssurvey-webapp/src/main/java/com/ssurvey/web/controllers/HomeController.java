package com.ssurvey.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ssurvey.service.SurveyService;

@Controller
public class HomeController extends SSurveyGenericController {

  @Autowired
  private SurveyService surveyService;

  @RequestMapping(value = "/", method = RequestMethod.GET)
  public ModelAndView showLogin(@RequestParam(value = "url", required = false) String url) {
    if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() != "anonymousUser") {
      ModelAndView mv = new ModelAndView("redirect:/surveys/");
      return mv;
    } else {
      ModelAndView mv = new ModelAndView("login");
      mv.addObject("url", url);
      return mv;
    }
  }

  @RequestMapping(value = "/error", method = RequestMethod.GET)
  public ModelAndView errorPage() {
    ModelAndView mv = this.createModelAndView("error");
    mv.addObject("errorMessage", "This is an error page.");
    return mv;
  }
}
