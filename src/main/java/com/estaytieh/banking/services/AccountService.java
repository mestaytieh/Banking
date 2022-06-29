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


//    account.ifPresent(value ->
//        value.setTransactions(transactionRepository
//            .findBySourceAccountIdOrderByInitiationDate(value.getId())));

   return account.orElse(null);
  }

  public List<Account> getAllAccounts()
  {
    return accountRepository.findAll();
  }
}
