package com.ssurvey.logic;


import java.util.List;

import org.jsoup.helper.Validate;

import com.ssurvey.model.LinkedInUserProfile;
import com.ssurvey.service.LinkedInInformationService;


public class IndexCalculator {
  
  
  private List<ProfileEvaluationRule> rules;
  
  public IndexCalculator(List<ProfileEvaluationRule> rules) {
    Validate.notNull(rules);
    
    this.rules = rules;
  }
  
  public float calculateIndex(LinkedInUserProfile profile){
    float totalWeight = 0;
    float totalScore = 0;
    for(ProfileEvaluationRule rule : this.rules){
      totalScore += rule.evaluate(profile);
      totalWeight += rule.getWeigth();
    }
    return totalScore / totalWeight;
  }
  
}
