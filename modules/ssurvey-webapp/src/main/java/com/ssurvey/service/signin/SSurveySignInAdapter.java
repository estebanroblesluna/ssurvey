package com.ssurvey.service.signin;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.SignInAdapter;

import com.ssurvey.model.Account;
import com.ssurvey.service.AccountService;

public class SSurveySignInAdapter implements SignInAdapter {

  private AccountService accountService;

  public AccountService getAccountService() {
    return accountService;
  }

  public void setAccountService(AccountService accountService) {
    this.accountService = accountService;
  }

  public String signIn(java.lang.String userId, Connection< ? > connection, org.springframework.web.context.request.NativeWebRequest request) {

    Account account = this.accountService.getAccountById(Long.parseLong(userId));
    User user = new SSurveyUser(account, userId);
    SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(user, null));
    String redirectUrl = (String) request.getNativeRequest(HttpServletRequest.class).getSession().getAttribute("redirect_url");
    return (!redirectUrl.isEmpty()) ? redirectUrl : "/surveys/";
  }

}
