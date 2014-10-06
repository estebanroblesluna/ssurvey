package com.ssurvey.model;

import com.ssurvey.model.linkedin.tickets.TicketVisitor;


public class GetConnectionTicket extends Ticket {
  
  private String connectionProfileId;
  private String connectionOfProfileId;
  int depth;
  
  public GetConnectionTicket(Long connectionOwnerId, String connectionProfileId, String connectionOfProfileId, int depth) {
    this.setTicketOwnerId(connectionOwnerId);
    this.connectionProfileId = connectionProfileId;
    this.connectionOfProfileId = connectionOfProfileId;
  }

  @Override
  public void accept(TicketVisitor visitor) {
    visitor.visit(this);
  }
  
  public String getConnectionProfileId() {
    return connectionProfileId;
  }

  
  public void setConnectionProfileId(String connectionProfileId) {
    this.connectionProfileId = connectionProfileId;
  }

  
  public String getConnectionOfProfileId() {
    return connectionOfProfileId;
  }

  
  public void setConnectionOfProfileId(String connectionOfProfileId) {
    this.connectionOfProfileId = connectionOfProfileId;
  }

  
  public int getDepth() {
    return depth;
  }

  
  public void setDepth(int depth) {
    this.depth = depth;
  }

  public GetConnectionTicket(){
    
  }



}
