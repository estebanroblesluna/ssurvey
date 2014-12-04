package com.ssurvey.logic.rules;

import com.ssurvey.model.LinkedInUserProfile;

public class RecommendersEvaluationRule extends ProfileEvaluationRule {

  private float maxRecommenders = 100f;
  private float fixRecommenders = 50f;

  public RecommendersEvaluationRule(float weight) {
    super(weight);
  }

  public float getFixRecommenders() {
    return fixRecommenders;
  }

  public void setFixRecommenders(float fixRecommenders) {
    this.fixRecommenders = fixRecommenders;
  }

  public float getMaxRecommenders() {
    return maxRecommenders;
  }

  public void setMaxRecommenders(float maxRecommenders) {
    this.maxRecommenders = maxRecommenders;
  }
  
  @Override
  protected float score(LinkedInUserProfile profile) {
    return Math.max(profile.getRecommenders().size(), maxRecommenders) * (fixRecommenders / maxRecommenders);
  }
}
