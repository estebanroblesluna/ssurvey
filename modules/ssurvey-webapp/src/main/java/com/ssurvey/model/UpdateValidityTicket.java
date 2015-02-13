package com.ssurvey.model;

import com.ssurvey.model.linkedin.tickets.TicketVisitor;


public class UpdateValidityTicket extends Ticket {

  @Override
  public void accept(TicketVisitor visitor) {
    visitor.visit(this);
  }
  
  public UpdateValidityTicket(){
    
  }
  
  public UpdateValidityTicket(long accountId){
    this.setTicketOwnerId(accountId);
  }

}
