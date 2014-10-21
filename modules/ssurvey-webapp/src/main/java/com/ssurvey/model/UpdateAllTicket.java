package com.ssurvey.model;

import com.ssurvey.model.linkedin.tickets.TicketVisitor;


public class UpdateAllTicket extends Ticket {

  @Override
  public void accept(TicketVisitor visitor) {
    visitor.visit(this);
  }
  
  public UpdateAllTicket(){
    
  }

}
