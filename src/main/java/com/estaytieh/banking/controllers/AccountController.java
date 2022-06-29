package com.estaytieh.banking.controllers;

//import com.example.paul.models.Account;
//import com.example.paul.services.AccountService;
//import com.example.paul.utils.AccountInput;
//import com.example.paul.utils.InputValidator;
import com.estaytieh.banking.models.Account;
import com.estaytieh.banking.models.Transaction;
import com.estaytieh.banking.services.AccountService;
import com.estaytieh.banking.services.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.*;

@RestController
@RequestMapping("api/accounts")
public class AccountController
{

  private static final Logger LOGGER = LoggerFactory.getLogger(AccountController.class);

  private static final String INVALID_SEARCH_CRITERIA =
      "The provided sort code or account number did not match the expected format";

  private static final String NO_ACCOUNT_FOUND =
      "Unable to find an account matching this sort code and account number";

  private final AccountService accountService;
  private final TransactionService transactionService;

  @Autowired
  public AccountController(AccountService accountService,TransactionService transactionService) {
    this.accountService = accountService;
    this.transactionService = transactionService;
  }

  @GetMapping(value = "/allAccounts", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> getAllAccounts()
  {
    LOGGER.debug("Triggered AccountController.accountInput");

    List<Account> accounts = accountService.getAllAccounts();
    List<Transaction> transactions = transactionService.getAllTransactions();


    accounts.stream()
        .forEach(account -> transactions.stream()
            .filter(transaction -> transaction.getAccountId() == account.getId() || transaction.getIban().equals(account.getIban()))
            .forEach(transaction -> {
              if (account.getTransactions() == null)
                account.setTransactions(new ArrayList<>());
              account.getTransactions().add(transaction);
            }));

    if (accounts == null) {
      return new ResponseEntity<>(NO_ACCOUNT_FOUND, HttpStatus.NO_CONTENT);
    } else {
      return new ResponseEntity<>(accounts, HttpStatus.OK);
    }

  }

  @RequestMapping(path = "/accountsByAccountNumber", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> checkAccountBalance(@RequestParam String accountNumber)
  {
    LOGGER.debug("Triggered AccountController.accountInput");

    Account account = accountService.getAccount(accountNumber);

//
//      // Return the account details, or warn that no account was found for given input
      if (account == null) {
        return new ResponseEntity<>(NO_ACCOUNT_FOUND, HttpStatus.NO_CONTENT);
      } else {
        return new ResponseEntity<>(account, HttpStatus.OK);
      }

  }

}
