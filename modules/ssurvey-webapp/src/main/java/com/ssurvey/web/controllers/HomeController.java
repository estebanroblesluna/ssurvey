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
import com.ssurvey.model.Account;
import com.ssurvey.model.QuestionType;
import com.ssurvey.model.RankAnswerQuestion;
import com.ssurvey.model.SingleChoiceQuestion;
import com.ssurvey.model.Survey;
import com.ssurvey.service.SurveyService;
import com.ssurvey.service.signin.SSurveyUser;

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
    // SSurveyUser user = (SSurveyUser)
    // SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    // mv.addObject("account", user.getAccount());
    MultipleChoiceQuestion multipleChoiceQuestion = new MultipleChoiceQuestion();
    multipleChoiceQuestion.setId(0);
    multipleChoiceQuestion.setName("Where do you get your news?");
    LinkedList<String> options = new LinkedList<String>();
    options.add("Internet");
    options.add("T.V.");
    options.add("Radio");
    options.add("Newspaper");
    options.add("Others");
    multipleChoiceQuestion.setOptions(options);

    SingleChoiceQuestion singleChoiceQuestion = new SingleChoiceQuestion();
    singleChoiceQuestion.setId(1);
    singleChoiceQuestion.setName("How good is my site?");
    options.clear();
    options.add("Excellent");
    options.add("Good");
    options.add("Can be improved");
    options.add("Bad");
    options.add("No comments");
    singleChoiceQuestion.setOptions(options);

    OpenAnswerQuestion openAnswerQuestion = new OpenAnswerQuestion();
    openAnswerQuestion.setId(2);
    openAnswerQuestion.setName("Opcional comment:");
    openAnswerQuestion.setUpperBound(1024);

    NumericAnswerQuestion numericAnswerQuestion = new NumericAnswerQuestion();
    numericAnswerQuestion.setId(3);
    numericAnswerQuestion.setName("Rank the company from 1 to 10.");
    numericAnswerQuestion.setLowerBound(1);
    numericAnswerQuestion.setUpperBound(10);

    RankAnswerQuestion rankAnswerQuestion = new RankAnswerQuestion();
    rankAnswerQuestion.setId(4);
    rankAnswerQuestion.setName("Priorize this:");
    options.clear();
    options.add("Internet");
    options.add("T.V.");
    options.add("Radio");
    options.add("Newspaper");
    rankAnswerQuestion.setOptions(options);

    LinkedList<Question> questions = new LinkedList<Question>();
    questions.add(multipleChoiceQuestion);
    questions.add(singleChoiceQuestion);
    questions.add(openAnswerQuestion);
    questions.add(rankAnswerQuestion);
    questions.add(numericAnswerQuestion);

    Survey survey = new Survey();
    survey.setQuestions(questions);
    survey.setId(0);
    survey.setName("Answer questions and feel great about it.");
    surveyService.saveSurvey(survey);
    mv.addObject("survey", survey);
    return mv;
  }

}
