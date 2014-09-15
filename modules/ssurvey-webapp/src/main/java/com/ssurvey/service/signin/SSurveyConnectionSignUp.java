package com.ssurvey.service.signin;

import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;

import com.ssurvey.model.Account;
import com.ssurvey.service.AccountService;

public class SSurveyConnectionSignUp implements ConnectionSignUp {

  private AccountService accountService;

  public AccountService getAccountService() {
    return accountService;
  }

  public void setAccountService(AccountService accountService) {
    this.accountService = accountService;
  }

  @Override
  public String execute(Connection< ? > connection) {
    Account account = new Account();
    account.setFirstName(connection.fetchUserProfile().getFirstName());
    account.setLastName(connection.fetchUserProfile().getLastName());
    account = this.getAccountService().createAccount(account);
    return account.getId().toString();
  }

}
