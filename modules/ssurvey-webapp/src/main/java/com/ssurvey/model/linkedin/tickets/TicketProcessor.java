package com.ssurvey.model.linkedin.tickets;

import java.util.List;

import org.jsoup.helper.Validate;
import org.springframework.social.connect.UsersConnectionRepository;

import com.ssurvey.model.AnsweredSurvey;
import com.ssurvey.model.GetConnectionTicket;
import com.ssurvey.model.GetRecommenderTicket;
import com.ssurvey.model.GetRespondentInformationTicket;
import com.ssurvey.model.LinkedInUserProfile;
import com.ssurvey.model.Ticket;
import com.ssurvey.service.AnswerService;
import com.ssurvey.service.LinkedInInformationService;
import com.ssurvey.service.TicketService;


public class TicketProcessor implements TicketVisitor{
  
  
  private static final int TICKETS_TO_PROCESS = 100;
  private TicketService ticketService;
  private AnswerService answerService;
  private LinkedInInformationService linkedinInformationService;
  
  
  public void processTickets(){
    int processed = 0;
    List<Ticket> toProcess = this.ticketService.getNonProcessedTickets(this.TICKETS_TO_PROCESS - processed);
    while(toProcess.size() != 0){
      for(Ticket t : toProcess){
        t.accept(this);
      }
      processed += toProcess.size();
      if(processed >= this.TICKETS_TO_PROCESS){
        break;
      }
      toProcess = this.ticketService.getNonProcessedTickets(this.TICKETS_TO_PROCESS - processed);
    }
  }

  public TicketProcessor(TicketService ticketService, AnswerService answerService, LinkedInInformationService linkedInInformationService){
    Validate.notNull(ticketService);
    Validate.notNull(answerService);
    Validate.notNull(linkedInInformationService);
    
    this.ticketService = ticketService;
    this.answerService = answerService;
    this.linkedinInformationService = linkedInInformationService;
  }
  
  
  public void process(Ticket ticket){
    ticket.accept(this);
  }

  @Override
  public void visit(GetRespondentInformationTicket ticket){
    AnsweredSurvey answeredSurvey = ticket.getSurvey();
    this.answerService.setAnswerLinkedInProfile(answeredSurvey, this.linkedinInformationService.updateProfile(ticket.getTicketOwnerId(), null, 1));
    this.ticketService.markAsProcessed(ticket);
  }
  
  @Override
  public void visit(GetRecommenderTicket ticket){
    LinkedInUserProfile recommendee = this.linkedinInformationService.getLinkedInUserProfile(ticket.getRecommendeeProfileId());
    LinkedInUserProfile recommender = this.linkedinInformationService.updateProfile(ticket.getTicketOwnerId(), ticket.getRecommenderProfileId(), 0);
    this.linkedinInformationService.addRecommendation(recommender, recommendee);
    
    this.ticketService.markAsProcessed(ticket);
  }

  @Override
  public void visit(GetConnectionTicket ticket) {
    LinkedInUserProfile connection = this.linkedinInformationService.updateProfile(ticket.getTicketOwnerId(),ticket.getConnectionProfileId(), ticket.getDepth());
    LinkedInUserProfile connectionOf = this.linkedinInformationService.getLinkedInUserProfile(ticket.getConnectionOfProfileId());
    this.linkedinInformationService.addConnection(connection, connectionOf);
    this.ticketService.markAsProcessed(ticket);
  }

}
