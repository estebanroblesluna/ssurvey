package com.ssurvey.service;

import java.util.List;

import org.jsoup.helper.Validate;
import org.springframework.transaction.annotation.Transactional;

import com.ssurvey.model.Ticket;
import com.ssurvey.repositories.TicketRepository;


public class TicketService {

  TicketRepository ticketRepository;
  
  public TicketService(TicketRepository ticketRepository){
    Validate.notNull(ticketRepository);
    this.ticketRepository = ticketRepository;
  }
  
  @Transactional
  public List<Ticket> getNonProcessedTickets(int quantity){
    return this.ticketRepository.getNonProcessedTickets(quantity);
  }
  
  @Transactional
  public void saveTicket(Ticket ticket){
    this.ticketRepository.save(ticket);
  }
  
}
