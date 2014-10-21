package com.ssurvey.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ssurvey.service.LinkedInInformationService;

@Controller
@RequestMapping("/admin")
public class AdminController {
  
  @Autowired
  private LinkedInInformationService linkedInInformationService;

  @RequestMapping("/update-data")
  public void updateLinkedInData(){
    this.linkedInInformationService.updateAllData();
  }
}
