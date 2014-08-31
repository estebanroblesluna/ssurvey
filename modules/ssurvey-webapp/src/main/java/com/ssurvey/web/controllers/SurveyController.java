package com.ssurvey.web.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ssurvey.model.Account;
import com.ssurvey.model.Survey;
import com.ssurvey.service.SurveyService;

@Controller
@RequestMapping("/surveys/")
public class SurveyController extends SSurveyGenericController {
	
	@Autowired
	private SurveyService surveyService;

	@RequestMapping("/{surveyId}")
	public ModelAndView renderSurvey(@PathVariable(value = "surveyId") Long surveyId){
		ModelAndView mv = this.createModelAndView("survey");
		Survey survey = this.surveyService.getSurveyById(surveyId);
		mv.addObject("survey", survey);
		return mv;
	}
	@RequestMapping("/")
	public ModelAndView showRecommendedSurveys(){
		ModelAndView mv = this.createModelAndView("recommended-surveys");
		List<Survey> surveys = this.surveyService.getSurveys();
		mv.addObject("surveys", surveys);
		return mv;
	}
	
	
}
