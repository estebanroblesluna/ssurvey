package com.ssurvey.service;

import java.util.Set;

import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.linkedin.api.LinkedIn;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssurvey.model.LinkedInUserProfile;
import com.ssurvey.repositories.GenericRepository;

@Service
public class LocalLinkedInInformationService {
  private GenericRepository repository;
  private UsersConnectionRepository usersConnectionRepository;
  
  public LocalLinkedInInformationService(GenericRepository repository, UsersConnectionRepository usersConnectionRepository) {
    this.repository = repository;
    this.usersConnectionRepository = usersConnectionRepository;
  }
  
  public LocalLinkedInInformationService() {
  }
  
  @Transactional
  public String getLinkedInId (Long accountId) {
    LinkedIn api = this.usersConnectionRepository.createConnectionRepository(accountId.toString())
                       .getPrimaryConnection(LinkedIn.class).getApi();
    return api.profileOperations().getProfileId();
  }
  
  /*
  @Transactional
  public Long getAccountId (String linkedInId) {
    
  }
  */
  
  @Transactional
  public Set<LinkedInUserProfile> getRecommenders (String linkedInId) {
    LinkedInUserProfile linkedInUserProfile = (LinkedInUserProfile) this.repository.get(LinkedInUserProfile.class, linkedInId);
    return linkedInUserProfile.getRecommenders();
  }
  
  @Transactional
  public Set<LinkedInUserProfile> getConnections (String linkedInId) {
    LinkedInUserProfile linkedInUserProfile = (LinkedInUserProfile) this.repository.get(LinkedInUserProfile.class, linkedInId);
    return linkedInUserProfile.getConnections();
  }
  
}
