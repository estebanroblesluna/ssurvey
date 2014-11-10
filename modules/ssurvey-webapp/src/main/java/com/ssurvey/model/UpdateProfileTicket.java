package com.ssurvey.model;

import com.ssurvey.model.linkedin.tickets.TicketVisitor;


public class UpdateProfileTicket extends Ticket {

  @Override
  public void accept(TicketVisitor visitor) {
    visitor.visit(this);
  }
  
  public UpdateProfileTicket(){
    
  }
  
  public UpdateProfileTicket(Long accountId){
    this.setTicketOwnerId(accountId);
  }

}
