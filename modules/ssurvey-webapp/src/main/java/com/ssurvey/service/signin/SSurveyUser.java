package com.ssurvey.service.signin;

import java.util.ArrayList;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.social.security.SocialUser;

import com.ssurvey.model.Account;

@SuppressWarnings("serial")
public class SSurveyUser extends SocialUser {

  private Account account;

  public Account getAccount() {
    return account;
  }

  public void setAccount(Account account) {
    this.account = account;
  }

  public SSurveyUser(Account account, String providerId) {
    super(providerId, "", new ArrayList<GrantedAuthority>());
    this.setAccount(account);
  }
}
