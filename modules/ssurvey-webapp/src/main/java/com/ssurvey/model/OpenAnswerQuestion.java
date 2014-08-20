package com.ssurvey.model;

import java.util.List;

public class OpenAnswerQuestion extends Question {

  private List<String> options;

  public List<String> getOptions() {
    return options;
  }

  public void setOptions(List<String> options) {
    this.options = options;
  }
}