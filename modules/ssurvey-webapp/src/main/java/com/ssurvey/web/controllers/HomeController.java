package com.ssurvey.web.controllers;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ssurvey.model.Survey;
import com.ssurvey.repositories.SurveyRepository;
import com.ssurvey.service.SurveyService;

@Controller
public class HomeController {
	
	@Autowired
	private SurveyService surveyService;
	
	@RequestMapping("/")
	public ModelAndView showHomePage(){
		Survey s = new Survey();
		s.setName("Test");
		surveyService.saveSurvey(s);
		ModelAndView mv = new ModelAndView("home");
		return mv;
	}
}
