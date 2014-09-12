package com.ssurvey.model;

import org.springframework.social.linkedin.api.Company;


public class LinkedInCompany {
  
  private int id;
  private String name;
  
  public LinkedInCompany(){
    
  }
  
  public LinkedInCompany(Company company){
    this.id = company.getId();
    this.setName(company.getName());
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
  
  
  
}
