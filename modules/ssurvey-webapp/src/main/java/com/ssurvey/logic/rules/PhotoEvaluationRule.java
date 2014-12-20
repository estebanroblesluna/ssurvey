package com.ssurvey.logic.rules;

import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.linkedin.api.LinkedIn;

import com.ssurvey.model.LinkedInUserProfile;
import com.ssurvey.service.LinkedInInformationService;

public class PhotoEvaluationRule extends ProfileEvaluationRule {
  
  private UsersConnectionRepository usersConnectionRepository;
  private LinkedInInformationService linkedInInformationService;
  
  public PhotoEvaluationRule(float weight, UsersConnectionRepository usersConnectionRepository, LinkedInInformationService linkedInInformationService) {
    super(weight);
    this.usersConnectionRepository = usersConnectionRepository;
    this.linkedInInformationService = linkedInInformationService;
  }
  
  @Override
  protected float score(LinkedInUserProfile profile) {
    LinkedIn linkedIn = this.usersConnectionRepository
    .createConnectionRepository(linkedInInformationService.
            getAccountForLinkedInProfile(profile.getId()).getId().toString())
                    .getPrimaryConnection(LinkedIn.class)
                    .getApi();
    String profilePictureUrl = linkedIn.profileOperations().getProfileById(profile.getId()).getProfilePictureUrl();
    return (profilePictureUrl != null) ? 1.0f : 0.0f;
  }
}
