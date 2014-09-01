package com.ssurvey.repositories;

import org.hibernate.SessionFactory;
import org.jsoup.helper.Validate;

import com.ssurvey.model.Account;

public class AccountRepository extends GenericRepository {

  public AccountRepository() {
  }

  public AccountRepository(SessionFactory sessionFactory) {
    Validate.notNull(sessionFactory);
    this.setSessionFactory(sessionFactory);
  }

  public Account createAccount(Account account) {
    account.setId((Long) this.getSessionFactory().getCurrentSession().save(account));
    return account;
  }

  public Account getAccountById(long id) {
    return this.get(Account.class, id);
  }
}
