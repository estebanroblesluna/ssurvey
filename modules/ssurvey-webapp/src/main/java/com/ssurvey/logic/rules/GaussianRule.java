package com.ssurvey.logic.rules;

import com.ssurvey.model.LinkedInUserProfile;

public abstract class GaussianRule extends ProfileEvaluationRule {

  private float expectation;
  private float variance;
  private static double SQRT2PI = Math.sqrt(Math.PI * 2.0f);

  public GaussianRule(float weight, float expectation, float variance) {
    super(weight);
  }
  protected float getExpectation() {
    return expectation;
  }

  protected float getVariance() {
    return variance;
  }

  protected abstract float evaluatedParam(LinkedInUserProfile profile);

  private float gaussianProbability(float x) {
    double squareDiff = (x - this.expectation) * (x - this.expectation);
    double standardDeviation = Math.sqrt(this.variance);
    return (float) ((float) (1.0f / standardDeviation * SQRT2PI) * Math.pow(Math.E, -(squareDiff / 2 * this.variance)));
  }

  public float score(LinkedInUserProfile profile) {
    float upperBound = gaussianProbability(this.expectation);
    return (gaussianProbability(this.evaluatedParam(profile)) / upperBound) * 2 - 1;
  }

}
