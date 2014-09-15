package com.ssurvey.util;

import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.social.linkedin.api.LinkedInDate;

public class LinkedInAPIHelper {
	
	public static Date convertDate(LinkedInDate linkedInDate){
	  if(linkedInDate == null){
	    return null;
	  }
		return new GregorianCalendar(linkedInDate.getYear(),linkedInDate.getMonth(),linkedInDate.getDay()).getTime();
	}
	
}
