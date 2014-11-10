package com.ssurvey.service;

import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.linkedin.api.Company;
import org.springframework.social.linkedin.api.LinkedIn;
import org.springframework.social.linkedin.api.LinkedInProfile;
import org.springframework.social.linkedin.api.LinkedInProfileFull;
import org.springframework.social.linkedin.api.Position;
import org.springframework.social.linkedin.api.Recommendation;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssurvey.model.GetConnectionTicket;
import com.ssurvey.model.GetRecommenderTicket;
import com.ssurvey.model.LinkedInCompany;
import com.ssurvey.model.LinkedInPosition;
import com.ssurvey.model.LinkedInUserProfile;
import com.ssurvey.repositories.GenericRepository;
import com.ssurvey.util.LinkedInAPIHelper;

@Service
public class LocalLinkedInInformationService {
  private GenericRepository repository;

  public LocalLinkedInInformationService(GenericRepository repository) {
    this.repository = repository;
  }

  public LocalLinkedInInformationService() {
  }

}
