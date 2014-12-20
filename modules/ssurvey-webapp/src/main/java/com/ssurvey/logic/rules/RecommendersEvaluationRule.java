package com.ssurvey.logic.rules;

import com.ssurvey.model.LinkedInUserProfile;

public class RecommendersEvaluationRule extends ProfileEvaluationRule {

  private float maxRecommenders;

  public RecommendersEvaluationRule(float weight, float maxRecommenders) {
    super(weight);
    this.maxRecommenders = maxRecommenders;
  }

  public float getMaxRecommenders() {
    return maxRecommenders;
  }

  public void setMaxRecommenders(float maxRecommenders) {
    this.maxRecommenders = maxRecommenders;
  }
  
  @Override
  protected float score(LinkedInUserProfile profile) {
    return Math.min(profile.getRecommenders().size(), maxRecommenders) / maxRecommenders;
  }
}
