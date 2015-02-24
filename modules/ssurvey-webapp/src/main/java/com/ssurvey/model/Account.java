package com.ssurvey.model;

public class Account {

  private Long id;
  private String firstName;
  private String middleName;
  private String lastName;
  private Long lastConfidenceUpdateTimestamp;

  public Account() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getMiddleName() {
    return middleName;
  }

  public void setMiddleName(String middleName) {
    this.middleName = middleName;
  }

  public String getFullName() {
    return this.firstName + " " + this.middleName + " " + this.lastName;
  }

  public Long getLastConfidenceUpdateTimestamp() {
    return lastConfidenceUpdateTimestamp;
  }

  public void setLastConfidenceUpdateTimestamp(Long lastConfidenceUpdateTimestamp) {
    this.lastConfidenceUpdateTimestamp = lastConfidenceUpdateTimestamp;
  }
}
