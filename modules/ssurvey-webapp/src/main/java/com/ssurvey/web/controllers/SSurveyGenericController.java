package com.ssurvey.web.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.ModelAndView;

import com.ssurvey.model.Account;
import com.ssurvey.service.signin.SSurveyUser;

public abstract class SSurveyGenericController {
	
  public Account getLoggedUser(){
    return ((SSurveyUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getAccount();
  }
  
	public ModelAndView createModelAndView(String template){
		ModelAndView mv = new ModelAndView(template);
		
		mv.addObject("user", this.getLoggedUser());
		return mv;
	}
	
}
