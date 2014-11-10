package com.ssurvey.model.linkedin.tickets;

import java.util.Date;
import java.util.List;

import org.jsoup.helper.Validate;

import com.ssurvey.logic.IndexCalculator;
import com.ssurvey.model.Account;
import com.ssurvey.model.AnsweredSurvey;
import com.ssurvey.model.GetConnectionTicket;
import com.ssurvey.model.GetRecommenderTicket;
import com.ssurvey.model.GetRespondentInformationTicket;
import com.ssurvey.model.LinkedInUserProfile;
import com.ssurvey.model.Ticket;
import com.ssurvey.model.UpdateAllTicket;
import com.ssurvey.model.UpdateConfidenceTicket;
import com.ssurvey.model.UpdateProfileTicket;
import com.ssurvey.service.AccountService;
import com.ssurvey.service.AnswerService;
import com.ssurvey.service.LinkedInInformationService;
import com.ssurvey.service.TicketService;

public class TicketProcessor implements TicketVisitor {

  private static final int CONFIDENCE_UPDATES_PER_BLOCK = 50;
  private static final int TICKETS_TO_PROCESS = 100;
  private TicketService ticketService;
  private AnswerService answerService;
  private AccountService accountService;
  private IndexCalculator confidenceCalculator;
  private LinkedInInformationService linkedinInformationService;

  public void processTickets() {
    int processed = 0;
    List<Ticket> toProcess = this.ticketService.getNonProcessedTickets(TICKETS_TO_PROCESS - processed);
    while (toProcess.size() != 0) {
      for (Ticket t : toProcess) {
        t.accept(this);
      }
      processed += toProcess.size();
      if (processed >= TICKETS_TO_PROCESS) {
        break;
      }
      toProcess = this.ticketService.getNonProcessedTickets(TICKETS_TO_PROCESS - processed);
    }
  }

  public void updateConfidence() {
    for (Account account : this.accountService.getAccountsForConfidenceUpdate(CONFIDENCE_UPDATES_PER_BLOCK)) {
      this.ticketService.saveTicket(new UpdateConfidenceTicket(account.getId()));
      account.setLastConfidenceUpdateTimestamp((new Date().getTime()));
    }
  }

  public TicketProcessor(TicketService ticketService, AnswerService answerService, LinkedInInformationService linkedInInformationService,
          AccountService accountService, IndexCalculator confidenceCalculator) {
    Validate.notNull(ticketService);
    Validate.notNull(answerService);
    Validate.notNull(linkedInInformationService);
    Validate.notNull(accountService);

    this.ticketService = ticketService;
    this.answerService = answerService;
    this.linkedinInformationService = linkedInInformationService;
    this.accountService = accountService;
    this.confidenceCalculator = confidenceCalculator;
  }

  public void process(Ticket ticket) {
    ticket.accept(this);
  }

  @Override
  public void visit(GetRespondentInformationTicket ticket) {
    AnsweredSurvey answeredSurvey = ticket.getSurvey();
    this.answerService.setAnswerLinkedInProfile(answeredSurvey, this.linkedinInformationService.updateProfile(ticket.getTicketOwnerId(), null, 1));
    this.ticketService.markAsProcessed(ticket);
  }

  @Override
  public void visit(GetRecommenderTicket ticket) {
    LinkedInUserProfile recommendee = this.linkedinInformationService.getLinkedInUserProfile(ticket.getRecommendeeProfileId());
    LinkedInUserProfile recommender = this.linkedinInformationService.updateProfile(ticket.getTicketOwnerId(), ticket.getRecommenderProfileId(), 0);
    this.linkedinInformationService.addRecommendation(recommender, recommendee);

    this.ticketService.markAsProcessed(ticket);
  }

  @Override
  public void visit(GetConnectionTicket ticket) {
    LinkedInUserProfile connection = this.linkedinInformationService.updateProfile(ticket.getTicketOwnerId(), ticket.getConnectionProfileId(),
            ticket.getDepth());
    LinkedInUserProfile connectionOf = this.linkedinInformationService.getLinkedInUserProfile(ticket.getConnectionOfProfileId());
    this.linkedinInformationService.addConnection(connection, connectionOf);
    this.ticketService.markAsProcessed(ticket);
  }

  @Override
  public void visit(UpdateProfileTicket ticket) {
    this.linkedinInformationService.updateProfile(ticket.getTicketOwnerId(), null, 1);
    this.ticketService.markAsProcessed(ticket);
  }

  @Override
  public void visit(UpdateAllTicket ticket) {
    for (Account account : this.accountService.getAccounts()) {
      this.ticketService.saveTicket(new UpdateProfileTicket(account.getId()));
    }
    this.ticketService.markAsProcessed(ticket);
  }

  @Override
  public void visit(UpdateConfidenceTicket ticket) {
    LinkedInUserProfile profile = this.linkedinInformationService.getLinkedInProfileForAccount(ticket.getTicketOwnerId());
    // Account account =
    // this.accountService.getAccountById(ticket.getTicketOwnerId());
    if (profile != null) {
      profile.setConfidence(this.confidenceCalculator.calculateIndex(profile));
      this.linkedinInformationService.updateProfileConfidence(profile);
    }
    this.ticketService.markAsProcessed(ticket);
  }

}
