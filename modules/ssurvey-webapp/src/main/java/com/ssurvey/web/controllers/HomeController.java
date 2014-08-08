package com.ssurvey.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class HomeController {
	
	@RequestMapping("/")
	public ModelAndView showHomePage(){
		ModelAndView mv = new ModelAndView("home");
		return mv;
	}
}
