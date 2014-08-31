package com.ssurvey.web.controllers;

import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ssurvey.model.MultipleChoiceQuestion;
import com.ssurvey.model.NumericAnswerQuestion;
import com.ssurvey.model.OpenAnswerQuestion;
import com.ssurvey.model.Question;
import com.ssurvey.model.RankAnswerQuestion;
import com.ssurvey.model.SingleChoiceQuestion;
import com.ssurvey.model.Survey;
import com.ssurvey.service.SurveyService;
import com.ssurvey.service.signin.SSurveyUser;

@Controller
public class HomeController extends SSurveyGenericController{

  @Autowired
  private SurveyService surveyService;

  @RequestMapping("/")
  public ModelAndView showLogin() {
    ModelAndView mv = new ModelAndView("login");
    return mv;
  }

}
