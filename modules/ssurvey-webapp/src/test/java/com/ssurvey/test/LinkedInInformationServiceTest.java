package com.ssurvey.test;

import static org.junit.Assert.*;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ssurvey.model.LinkedInUserProfile;
import com.ssurvey.service.LinkedInInformationService;

public class LinkedInInformationServiceTest {
  
  @Test
  public void testFacundoGalanConfidence() {
	try {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("/applicationContext.xml");
		LinkedInInformationService linkedInInformationService = (LinkedInInformationService) applicationContext.getBean("linkedInInformationService");
		LinkedInUserProfile profile = linkedInInformationService.getLinkedInUserProfile("u1BQ4p_D-r");
		assertEquals("Facundo Galan's confidence tolerance should be between 0.35 and 0.65", 0.5f, profile.getConfidence(), 0.15);
	} catch (Exception e) {}
  }
  
}
