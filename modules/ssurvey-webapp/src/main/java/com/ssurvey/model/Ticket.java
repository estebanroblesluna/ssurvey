package com.ssurvey.model;

import com.ssurvey.model.linkedin.tickets.TicketVisitor;

public abstract class Ticket {
  private long id;
  private boolean processed;
  private long ticketOwnerId;

  public Ticket(){
    this.processed = false;
  }
  
  
  public long getTicketOwnerId() {
    return ticketOwnerId;
  }

  
  public void setTicketOwnerId(long ticketOwnerId) {
    this.ticketOwnerId = ticketOwnerId;
  }

  public boolean isProcessed() {
    return processed;
  }
  public void setProcessed(boolean processed) {
    this.processed = processed;
  }
  
  public abstract void accept(TicketVisitor visitor);


  public long getId() {
    return id;
  }


  public void setId(long id) {
    this.id = id;
  }
  

}
