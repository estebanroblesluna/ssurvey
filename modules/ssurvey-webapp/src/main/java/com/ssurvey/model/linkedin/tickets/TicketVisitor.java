package com.ssurvey.model.linkedin.tickets;

import com.ssurvey.model.GetConnectionTicket;
import com.ssurvey.model.GetRecommenderTicket;
import com.ssurvey.model.GetRespondentInformationTicket;
import com.ssurvey.model.UpdateAllTicket;
import com.ssurvey.model.UpdateConfidenceTicket;
import com.ssurvey.model.UpdateProfileTicket;
import com.ssurvey.model.UpdateValidityTicket;


public interface TicketVisitor {
  
  public void visit(GetRespondentInformationTicket ticket);

  public void visit(GetRecommenderTicket ticket);

  public void visit(GetConnectionTicket ticket);
  
  public void visit(UpdateProfileTicket ticket);
  
  public void visit(UpdateAllTicket ticket);
  
  public void visit(UpdateConfidenceTicket ticket);

  public void visit(UpdateValidityTicket ticket);
  
}
