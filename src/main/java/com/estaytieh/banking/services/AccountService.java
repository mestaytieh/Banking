package com.estaytieh.banking.services;

import com.estaytieh.banking.models.Account;
import com.estaytieh.banking.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

  public List<Account> getAccountsBytUserId(Long userId) {
    return accountRepository
        .findAccountsByUserId(userId);
  }
  public List<Long> getAccountIdBytUserId(Long userId) {
    List<Account> accounts = getAccountsBytUserId(userId);
    return  accounts.stream()
        .map(account -> account.getId()) // <<< this
        .collect(Collectors.toList());
  }
  public Double getUserBalance(Long userId) {
    Double balance = 0.0;
    List<Account> accounts = getAccountsBytUserId(userId);

    return accounts.stream()
        .mapToDouble(x -> x.getCurrentBalance())
        .sum();
  }

  public List<Account> getAllAccounts()
  {
    return accountRepository.findAll();
  }

  public Account saveAccount(Account account)
  {
    return accountRepository.save(account);
  }

  public Long createNewAccount(Long userId,Double initialCredit)
  {
    Account account = new Account();
    account.setUserId(userId);
    account.setCurrentBalance(initialCredit);
    return saveAccount(account).getId();
  }

}
