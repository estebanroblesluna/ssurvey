package com.ssurvey.logic;

import java.util.List;

import org.jsoup.helper.Validate;

import com.ssurvey.logic.rules.ProfileEvaluationRule;
import com.ssurvey.model.LinkedInUserProfile;

public class ValidityCalculator {

  private List<ProfileEvaluationRule> rules;

  public ValidityCalculator(List<ProfileEvaluationRule> rules) {
    Validate.notNull(rules);
    this.rules = rules;
  }
  
  //TODO rewrite
  public float validityCalculator(LinkedInUserProfile profile) {
    float totalWeight = 0.0f;
    float totalScore = 0.0f;
    for (ProfileEvaluationRule rule : this.rules) {
      totalScore += rule.evaluate(profile);
      totalWeight += rule.getWeigth();
    }
    return totalScore/totalWeight;
  }

}
