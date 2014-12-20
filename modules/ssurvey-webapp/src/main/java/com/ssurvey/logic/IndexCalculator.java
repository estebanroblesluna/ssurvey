package com.ssurvey.logic;

import java.util.List;

import org.jsoup.helper.Validate;

import com.ssurvey.logic.rules.ProfileEvaluationRule;
import com.ssurvey.model.LinkedInUserProfile;

public class IndexCalculator {

  private List<ProfileEvaluationRule> rules;

  public IndexCalculator(List<ProfileEvaluationRule> rules) {
    Validate.notNull(rules);

    this.rules = rules;
  }

  public float confidenceCalculator(LinkedInUserProfile profile) {
    float totalWeight = 0.0f;
    float totalScore = 0.0f;
    for (ProfileEvaluationRule rule : this.rules) {
      totalScore += rule.evaluate(profile);
      totalWeight += rule.getWeigth();
    }
    return totalScore/totalWeight;
  }

}
