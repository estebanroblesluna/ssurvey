package com.ssurvey.model;

import java.util.Date;

import org.springframework.social.linkedin.api.Position;

import com.ssurvey.util.LinkedInAPIHelper;

public class LinkedInPosition {
	
	private String id;
	private LinkedInCompany company;
	private Date startDate;
	private Date endDate;
	
	public LinkedInPosition(){
		
	}
	
	public LinkedInPosition(Position linkedinPosition){
	  this.id = linkedinPosition.getId();
	  this.company = new LinkedInCompany(linkedinPosition.getCompany());
		this.setEndDate(LinkedInAPIHelper.convertDate(linkedinPosition.getEndDate()));
		this.setStartDate(LinkedInAPIHelper.convertDate(linkedinPosition.getStartDate()));
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}



	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

  public LinkedInCompany getCompany() {
    return company;
  }

  public void setCompany(LinkedInCompany company) {
    this.company = company;
  }
	
}
