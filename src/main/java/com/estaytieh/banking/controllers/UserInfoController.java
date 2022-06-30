package com.estaytieh.banking.controllers;

import com.estaytieh.banking.models.Transaction;
import com.estaytieh.banking.models.Userinfo;
import com.estaytieh.banking.services.AccountService;
import com.estaytieh.banking.services.TransactionService;
import com.estaytieh.banking.services.UserInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/userinfo")
public class UserInfoController {

  private static final Logger LOGGER = LoggerFactory.getLogger(UserInfoController.class);
  private static final String USER_NOT_FOUND =
      "Unable to find the user requested";

  private final UserInfoService userInfoService;
  private final AccountService accountService;
  private final TransactionService transactionService;

  public UserInfoController(UserInfoService userInfoService,AccountService accountService,TransactionService transactionService) {
    this.userInfoService = userInfoService;
    this.accountService = accountService;
    this.transactionService = transactionService;
  }

  @RequestMapping(path = "/userbyCustomerId/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> getUserByCustomerId(@PathVariable String id)
  {
    LOGGER.debug("Triggered UserInfoController userIfno");

    Userinfo user = userInfoService.getUserInfo(id);

//
//      // Return the account details, or warn that no account was found for given input
    if (user == null) {
      return new ResponseEntity<>(USER_NOT_FOUND, HttpStatus.NO_CONTENT);
    } else {
      return new ResponseEntity<>(user, HttpStatus.OK);
    }

  }

  @RequestMapping(path = "/userBalance/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> getUserBalance(@PathVariable String id)
  {
    LOGGER.debug("Triggered UserInfoController.userBalance");

      Double balance = accountService.getUserBalance(Long.parseLong(id));
//
//      // Return the account details, or warn that no account was found for given input
    if (balance == null) {
      return new ResponseEntity<>(USER_NOT_FOUND, HttpStatus.NO_CONTENT);
    } else {
      return new ResponseEntity<>(balance, HttpStatus.OK);
    }



  }

  @RequestMapping(path = "/userTransactions/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> getUserTransactions(@PathVariable String id)
  {
    LOGGER.debug("Triggered UserInfoController.userBalance");

    List<Long> accounts = accountService.getAccountIdBytUserId(Long.parseLong(id));
    List<Transaction> transactions = new ArrayList<>();
    for (Long accountId: accounts)
    {
      transactions.addAll(transactionService.getAllTransactionsByAccountId(accountId));
    }
//
//      // Return the account details, or warn that no account was found for given input
    if (transactions == null) {
      return new ResponseEntity<>(USER_NOT_FOUND, HttpStatus.NO_CONTENT);
    } else {
      return new ResponseEntity<>(transactions, HttpStatus.OK);
    }



  }


}
