package com.estaytieh.banking.services;

import com.estaytieh.banking.models.Account;
import com.estaytieh.banking.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService
{
  @Autowired
  private AccountRepository accountRepository;

  public Account getAccount(String accountNumber) {
    Optional<Account> account = accountRepository
        .findByAccountNumber( accountNumber);

   return account.orElse(null);
  }

  public List<Account> getAccountsBytUserId(String userId) {
    return accountRepository
        .findAccountsByUserId( userId);
  }
  public List<Account> getAllAccounts()
  {
    return accountRepository.findAll();
  }

  public Account saveAccount(Account account)
  {
    return accountRepository.save(account);
  }
}
