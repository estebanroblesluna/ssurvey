package com.ssurvey.repositories;

import org.springframework.stereotype.Repository;

import com.ssurvey.model.LinkedInCompany;

@Repository
public class LinkedInCompanyRepository extends GenericRepository{
  
  public LinkedInCompany getCompany(int companyId){
    return this.get(LinkedInCompany.class, companyId);
  }
  
  public void saveCompany(LinkedInCompany company){
    this.save(company);
  }
}
