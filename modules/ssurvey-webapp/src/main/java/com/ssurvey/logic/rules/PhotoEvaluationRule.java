package com.ssurvey.logic.rules;

import com.ssurvey.model.LinkedInUserProfile;

public class PhotoEvaluationRule extends ProfileEvaluationRule {
  
  public PhotoEvaluationRule(float weight) {
    super(weight);
  }
  
  @Override
  protected float score(LinkedInUserProfile profile) {
	  return (profile.getProfilePictureUrl() != null) ? 1.0f : 0.0f;
  }
}
