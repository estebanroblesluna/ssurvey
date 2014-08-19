package com.ssurvey.model;

import java.util.List;

public class MultipleChoiceQuestion extends Question {

  private List<String> options;
  private long upperBound;

  public List<String> getOptions() {
    return options;
  }

  public void setOptions(List<String> options) {
    this.options = options;
  }

  public long getUpperBound() {
    return upperBound;
  }

  public void setUpperBound(long upperBound) {
    this.upperBound = upperBound;
  }
}
