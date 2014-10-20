package com.ssurvey.service;

import java.util.List;

import org.jsoup.helper.Validate;
import org.springframework.transaction.annotation.Transactional;

import com.ssurvey.model.Account;
import com.ssurvey.repositories.AccountRepository;

public class AccountService {
	
	private AccountRepository accountRepository;
	
	public AccountService(AccountRepository accountRepository){
	  Validate.notNull(accountRepository);
	  
		this.accountRepository = accountRepository;
	}
	
	@Transactional
	public Account getAccountById(long id){
		return this.accountRepository.getAccountById(id);
	}
	
	@Transactional
	public List<Account> getAccounts(){
	  return this.accountRepository.list(Account.class);
	}
	
	@Transactional
	public Account createAccount(Account account){
		return this.accountRepository.createAccount(account);
	}
}
