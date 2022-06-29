package com.estaytieh.banking.controllers;

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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/transactions")
public class TransactionController {

  private static final Logger LOGGER = LoggerFactory.getLogger(TransactionController.class);

  private static final String TRANSACTION_FAILED =
      "Failed to create new transaction";

  private final AccountService accountService;
  private final TransactionService transactionService;

  @Autowired
  public TransactionController(AccountService accountService,TransactionService transactionService) {
    this.accountService = accountService;
    this.transactionService = transactionService;
  }

  @GetMapping(value = "/createTransaction", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> makeTransfer() {

    Transaction newtransaction = new Transaction();
    newtransaction.setAccountId(1);
    newtransaction.setIban("WB111321322");
    newtransaction.setAmount((long) 123.456);
    newtransaction.setCreationDate(new Timestamp(System.currentTimeMillis()));
    newtransaction.setTransactionReason("manyake");
    boolean isComplete = transactionService.createTransaction(newtransaction);
    if (isComplete){
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
        return new ResponseEntity<>(TRANSACTION_FAILED, HttpStatus.NO_CONTENT);
      } else {
        return new ResponseEntity<>(accounts, HttpStatus.OK);
      }
    } else {
      return new ResponseEntity<>(TRANSACTION_FAILED, HttpStatus.BAD_REQUEST);
    }
  }

}
