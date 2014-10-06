package com.ssurvey.repositories;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.jsoup.helper.Validate;
import org.springframework.stereotype.Repository;

import com.ssurvey.model.Ticket;

@Repository
public class TicketRepository extends GenericRepository{
  
  public TicketRepository(SessionFactory sessionFactory) {
    Validate.notNull(sessionFactory);
    this.setSessionFactory(sessionFactory);
  }
  
  @SuppressWarnings("unchecked")
  public List<Ticket> getNonProcessedTickets(int quantity){
    return this.getSessionFactory().getCurrentSession().createCriteria(Ticket.class)
      .add(Restrictions.eq("processed", false))
      .setMaxResults(quantity)
      .list();
  }
  
}
