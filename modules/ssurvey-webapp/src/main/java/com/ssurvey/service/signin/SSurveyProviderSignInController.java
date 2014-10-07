package com.ssurvey.service.signin;

import javax.servlet.http.HttpServletRequest;

import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.web.ProviderSignInController;
import org.springframework.social.connect.web.SignInAdapter;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.view.RedirectView;

public class SSurveyProviderSignInController extends ProviderSignInController {
  
  public SSurveyProviderSignInController (ConnectionFactoryLocator connectionFactoryLocator, UsersConnectionRepository usersConnectionRepository, SignInAdapter signInAdapter) {
    super(connectionFactoryLocator, usersConnectionRepository, signInAdapter);
  }
  
  @RequestMapping(value="/{providerId}", method=RequestMethod.POST, params="url")
  public RedirectView signIn (@PathVariable String providerId, NativeWebRequest request) {
    request.getNativeRequest(HttpServletRequest.class)
      .getSession()
      .setAttribute("redirect_url", request.getParameter("url"));
    return super.signIn(providerId, request);
  }

}
