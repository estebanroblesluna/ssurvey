package com.ssurvey.model;

import com.ssurvey.model.linkedin.tickets.TicketVisitor;


public class GetRespondentInformationTicket extends Ticket{
  
  private AnsweredSurvey survey;
  
  public GetRespondentInformationTicket(){
    
  }
  
  public GetRespondentInformationTicket(AnsweredSurvey survey, Long userId) {
    this.setSurvey(survey);
    this.setTicketOwnerId(userId);
  }


  @Override
  public void accept(TicketVisitor visitor) {
    visitor.visit(this);
  }


  public AnsweredSurvey getSurvey() {
    return survey;
  }


  public void setSurvey(AnsweredSurvey survey) {
    this.survey = survey;
  }
  
}
