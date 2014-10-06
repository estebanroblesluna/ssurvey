package com.ssurvey.model;

import com.ssurvey.model.linkedin.tickets.TicketVisitor;


public class GetRecommenderTicket extends Ticket {
  
  private String recommendeeProfileId;
  private String recommenderProfileId;
  
  public GetRecommenderTicket(){
    
  }

  public GetRecommenderTicket(Long connectionOwnerId, String recommenderProfileId, String recommendeeProfileId) {
    this.setTicketOwnerId(connectionOwnerId);
    this.recommendeeProfileId = recommendeeProfileId;
    this.recommenderProfileId = recommenderProfileId;
  }

  @Override
  public void accept(TicketVisitor visitor) {
    visitor.visit(this);
  }

  public String getRecommendeeProfileId() {
    return recommendeeProfileId;
  }

  public void setRecommendeeProfileId(String recommendeeProfileId) {
    this.recommendeeProfileId = recommendeeProfileId;
  }

  public String getRecommenderProfileId() {
    return recommenderProfileId;
  }

  public void setRecommenderProfileId(String recommenderProfileId) {
    this.recommenderProfileId = recommenderProfileId;
  }




}
