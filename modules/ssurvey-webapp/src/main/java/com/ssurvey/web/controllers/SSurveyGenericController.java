package com.ssurvey.web.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.ModelAndView;

import com.ssurvey.model.Account;
import com.ssurvey.service.signin.SSurveyUser;

public abstract class SSurveyGenericController {
	
	public ModelAndView createModelAndView(String template){
		ModelAndView mv = new ModelAndView(template);
		Account account = ((SSurveyUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getAccount();
		mv.addObject("user", account);
		return mv;
	}
	
}
