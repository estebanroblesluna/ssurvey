package com.ssurvey.model;

import com.ssurvey.model.linkedin.tickets.TicketVisitor;


public class UpdateConfidenceTicket extends Ticket {

  @Override
  public void accept(TicketVisitor visitor) {
    visitor.visit(this);
  }
  
  public UpdateConfidenceTicket(){
    
  }
  
  public UpdateConfidenceTicket(long accountId){
    this.setTicketOwnerId(accountId);
  }

}
