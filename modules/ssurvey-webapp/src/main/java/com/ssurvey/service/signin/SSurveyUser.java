package com.ssurvey.service.signin;

import java.util.ArrayList;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.social.security.SocialUser;

import com.ssurvey.model.Account;

public class SSurveyUser extends SocialUser{
	private Account account;
	
	public SSurveyUser(Account account, String providerId){
		super(providerId, "",  new ArrayList<GrantedAuthority>());
		this.account = account;
	}
}
