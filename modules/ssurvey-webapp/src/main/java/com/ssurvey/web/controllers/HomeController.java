package com.ssurvey.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ssurvey.model.Survey;
import com.ssurvey.service.SurveyService;

@Controller
public class HomeController {
	
	@Autowired
	private SurveyService surveyService;
	
	@RequestMapping("/")
	public ModelAndView showLogin(){
		ModelAndView mv = new ModelAndView("login");
		return mv;
		
	}
	@RequestMapping("/questions")
	public ModelAndView showQuestions(){
		ModelAndView mv = new ModelAndView("questions");
		return mv;
		
	}
}
