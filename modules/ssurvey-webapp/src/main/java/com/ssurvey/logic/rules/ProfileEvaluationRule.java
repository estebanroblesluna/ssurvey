package com.ssurvey.logic.rules;

import com.ssurvey.model.LinkedInUserProfile;

public abstract class ProfileEvaluationRule {

  private float weight;

  public ProfileEvaluationRule(float weight) {
    this.weight = weight;
  }

  /**
   * This method evaluates the profile with the rule.
   * 
   * @param profile
   *          is a LinkedIn profile to evaluate using the rule.
   * 
   * @return A value between <b>0</b> and <b>1</b> multiplied to rule's weight,
   *         representing this profile's score according to this rule.
   * 
   * */
  public float evaluate(LinkedInUserProfile profile) {
    return this.score(profile) * this.getWeigth();
  }

  /**
   * @return A value between <b>0</b> and <b>1</b>, indicating the weight of the
   *         rule: the higher the weight, more important the rule is.
   * */
  public float getWeigth() {
    return this.weight;
  }

  /**
   * @param profile
   *          Is a LinkedIn profile to evaluate using the rule.
   * 
   * @return A value between <b>0</b> and <b>1</b>, indicating the score of the
   *         evaluation of the profile.
   * */
  protected abstract float score(LinkedInUserProfile profile);

}
