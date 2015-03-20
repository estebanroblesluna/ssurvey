package com.ssurvey.logic;

import java.util.List;

import com.ssurvey.model.LinkedInPosition;
import com.ssurvey.model.LinkedInUserProfile;
import com.ssurvey.service.LinkedInInformationService;

public class ValidityCalculator {
  
  private LinkedInInformationService linkedInInformationService;
  
  public ValidityCalculator(LinkedInInformationService linkedInInformationService) {
	this.linkedInInformationService = linkedInInformationService;
  }

  public float validityCalculator(LinkedInUserProfile profile) {
	float totalWeight = 0.0f;
	List<LinkedInPosition> positions = this.linkedInInformationService.getPositions(profile.getId());
    for (LinkedInPosition position: positions) {
    	if (position.getCompany().getName().toLowerCase() == "lifia") {
    		totalWeight += 1.0f;
    	}
    }
    return totalWeight;
  }

}
