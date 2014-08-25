package com.ssurvey.web.controllers;

import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ssurvey.model.MultipleChoiceQuestion;
import com.ssurvey.model.Question;
import com.ssurvey.model.Survey;
import com.ssurvey.service.SurveyService;

@Controller
public class HomeController {

  @Autowired
  private SurveyService surveyService;

  @RequestMapping("/")
  public ModelAndView showLogin() {
    ModelAndView mv = new ModelAndView("login");
    return mv;
  }

  @RequestMapping("/questions")
  public ModelAndView showQuestions() {
    ModelAndView mv = new ModelAndView("questions");
    MultipleChoiceQuestion multipleChoiceQuestion = new MultipleChoiceQuestion();
    multipleChoiceQuestion.setId(0);
    multipleChoiceQuestion.setName("Which are true of these?");
    LinkedList<String> options = new LinkedList<String>();
    options.add("True");
    options.add("False");
    options.add("True 2");
    options.add("False 2");
    multipleChoiceQuestion.setOptions(options);
    multipleChoiceQuestion.setUpperBound(4);
    LinkedList<Question> questions = new LinkedList<Question>();
    questions.add(multipleChoiceQuestion);
    Survey survey = new Survey();
    survey.setQuestions(questions);
    survey.setId(0);
    survey.setName("Answer questions and feel great about it.");
    mv.addObject("survey", survey);
    return mv;
  }
}
