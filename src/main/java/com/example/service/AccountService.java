package com.example.service;

import org.springframework.stereotype.Service;

import com.example.entity.Account;
import com.example.repository.AccountRepository;
import com.example.exception.CustomExceptions.*;


@Service
public class AccountService {

  private AccountRepository accountRepository;

  public AccountService(AccountRepository accountRepository){
    this.accountRepository = accountRepository;
  }

  public Account login(Account account){
    return accountRepository.findByUsernameAndPassword(account.getUsername(), account.getPassword()).orElseThrow(() -> new InvalidUsernameOrPasswordException("Invalid username or password."));
  }

  public Account registerAccount(Account account){
    if(validRegistration(account)){
      return accountRepository.save(account);
    } 
    return null;
  }

  public boolean validRegistration(Account account){
    if(account.getUsername().isEmpty()){
      throw new InputInvalidException("Username cannot be empty.");
    }else if(account.getPassword().length() < 4){
      throw new InputInvalidException("Password must longer.");
    }else if(accountRepository.findByUsername(account.getUsername()).isPresent()){
      throw new UsernameAlreadyTakenException("Username already taken.");
    } else {
    return true;
    }
  }

}
