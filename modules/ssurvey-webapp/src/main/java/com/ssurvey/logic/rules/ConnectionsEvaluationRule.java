package com.ssurvey.logic.rules;

import com.ssurvey.model.LinkedInUserProfile;
import com.ssurvey.service.LinkedInInformationService;

public class ConnectionsEvaluationRule extends ProfileEvaluationRule {

  private float maxConnections;
  private LinkedInInformationService linkedInInformationService;

  public ConnectionsEvaluationRule(float weight, float maxConnections, LinkedInInformationService linkedInInformationService) {
    super(weight);
    this.maxConnections = maxConnections;
    this.linkedInInformationService = linkedInInformationService;
  }

  public float getMaxConnections() {
    return maxConnections;
  }

  public void setMaxConnections(float maxConnections) {
    this.maxConnections = maxConnections;
  }
  
  @Override
  protected float score(LinkedInUserProfile profile) {
    return Math.min(linkedInInformationService.getLinkedInUserConnectionsSize(profile.getId()), this.maxConnections) / this.maxConnections;
  }
}
