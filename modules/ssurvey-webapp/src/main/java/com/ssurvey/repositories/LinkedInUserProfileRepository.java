package com.ssurvey.repositories;

import org.hibernate.SessionFactory;
import org.jsoup.helper.Validate;
import org.springframework.stereotype.Repository;

import com.ssurvey.model.LinkedInUserProfile;
import com.ssurvey.repositories.GenericRepository;

@Repository
public class LinkedInUserProfileRepository extends GenericRepository {
  
  public LinkedInUserProfileRepository(SessionFactory sessionFactory) {
    super(sessionFactory);
  }
  
  
  @SuppressWarnings("unchecked")
  public LinkedInUserProfile getLinkedinUserProfile(String linkedInId){
    return (LinkedInUserProfile) this.getSessionFactory().getCurrentSession().get(LinkedInUserProfile.class, linkedInId);
  }
  
}
