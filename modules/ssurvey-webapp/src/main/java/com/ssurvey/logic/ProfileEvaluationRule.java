package com.ssurvey.logic;
import com.ssurvey.model.LinkedInUserProfile;
import com.ssurvey.service.LinkedInInformationService;


public interface ProfileEvaluationRule {
  
  public float evaluate(LinkedInUserProfile profile);
  
  public float getWeigth();
  
}
