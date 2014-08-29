package com.ssurvey.repositories;

import org.hibernate.SessionFactory;

import com.ssurvey.model.Account;

public class AccountRepository {

  private SessionFactory sessionFactory;

  public SessionFactory getSessionFactory() {
    return sessionFactory;
  }

  public void setSessionFactory(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }

  public Account createAccount(Account account) {
    account.setId((Long) this.sessionFactory.getCurrentSession().save(account));
    return account;
  }

  public Account getAccountById(long id) {
    return (Account) this.sessionFactory.getCurrentSession().get(Account.class, id);
  }
}
