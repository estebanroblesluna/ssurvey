package com.ssurvey.service;

import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.linkedin.api.LinkedIn;
import org.springframework.social.linkedin.api.LinkedInProfile;
import org.springframework.social.linkedin.api.LinkedInProfileFull;
import org.springframework.social.linkedin.api.Position;
import org.springframework.social.linkedin.api.Recommendation;
import org.springframework.transaction.annotation.Transactional;

import com.ssurvey.model.LinkedInUserProfile;
import com.ssurvey.repositories.LinkedInUserProfileRepository;

public class LinkedInInformationService {

	private ConnectionRepository connectionRepository;
	private LinkedInUserProfileRepository linkedInUserProfileRepository;
	
	public LinkedInInformationService(ConnectionRepository connectionRepository, LinkedInUserProfileRepository linkedInUserProfileRepository){
		this.connectionRepository = connectionRepository;
		this.linkedInUserProfileRepository = linkedInUserProfileRepository;
	}
	
	public LinkedInInformationService(){
	}
	
	@Transactional
	public LinkedInUserProfile getUserLinkedInInformation(String linkedInId){
	  return this.linkedInUserProfileRepository.getLinkedinUserProfile(linkedInId);
	}
	
	public LinkedInUserProfile getRespondentInformation(){

		LinkedIn linkedIn = this.connectionRepository.getPrimaryConnection(LinkedIn.class).getApi();
		LinkedInProfileFull fullProfile = linkedIn.profileOperations().getProfileFullById(linkedIn.profileOperations().getProfileId());
    LinkedInUserProfile linkedInUserProfile = new LinkedInUserProfile(fullProfile);
    for(Recommendation recommendation : fullProfile.getRecommendationsReceived()){
      linkedInUserProfile.addRecommender(new LinkedInUserProfile(linkedIn.profileOperations()
              .getProfileFullById(recommendation.getRecommender().getId())));
    }
    for(LinkedInProfile connectionProfile : linkedIn.connectionOperations().getConnections()){
      linkedInUserProfile.addConnection(new LinkedInUserProfile(linkedIn.profileOperations().getProfileFullById(connectionProfile.getId())));
    }
    this.linkedInUserProfileRepository.save(linkedInUserProfile);
    return linkedInUserProfile;
	}

}
