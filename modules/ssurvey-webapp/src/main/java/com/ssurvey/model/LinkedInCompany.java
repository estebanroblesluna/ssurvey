package com.ssurvey.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.springframework.social.linkedin.api.Company;


public class LinkedInCompany {
  
  private int id;
  private String name;
  private String url;
  
  public LinkedInCompany(){
    
  }
  
  public LinkedInCompany(Company company){
    this.setId(company.getId());
    this.setName(company.getName());
    this.setUrl(company.getWebsiteUrl());
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
  
  @Override
  public boolean equals(Object obj){
    if(! (obj instanceof LinkedInCompany)){
      return false;
    }
    if(obj == this){
      return true;
    }
    LinkedInCompany rhs = (LinkedInCompany) obj;
    return new EqualsBuilder().append(this.getId(), rhs.getId()).build();
  }
  
  @Override
  public int hashCode(){
    return new HashCodeBuilder().append(this.getId()).build();
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }
  
  
}
