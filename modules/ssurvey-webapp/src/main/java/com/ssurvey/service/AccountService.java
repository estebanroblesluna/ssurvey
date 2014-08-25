package com.ssurvey.service;

import org.springframework.transaction.annotation.Transactional;

import com.ssurvey.model.Account;
import com.ssurvey.repositories.AccountRepository;

public class AccountService {
	
	private AccountRepository accountRepository;
	
	public AccountService(){
		
	}

	public AccountRepository getAccountRepository() {
		return accountRepository;
	}

	public void setAccountRepository(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}
	
	@Transactional
	public Account getAccountById(long id){
		return this.accountRepository.getAccountById(id);
	}
	
	@Transactional
	public Account createAccount(Account account){
		return this.accountRepository.createAccount(account);
	}
}
