package com.ssurvey.model.linkedin.tickets;

import com.ssurvey.model.GetConnectionTicket;
import com.ssurvey.model.GetRecommenderTicket;
import com.ssurvey.model.GetRespondentInformationTicket;


public interface TicketVisitor {
  
  public void visit(GetRespondentInformationTicket ticket);

  public void visit(GetRecommenderTicket getRecommenderTicket);

  public void visit(GetConnectionTicket getConnectionTicket);
  
}
