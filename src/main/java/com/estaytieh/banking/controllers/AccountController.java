package com.estaytieh.banking.controllers;


import com.estaytieh.banking.forms.CreateAccountForm;
import com.estaytieh.banking.inputs.AccountCreationInput;
import com.estaytieh.banking.models.Account;
import com.estaytieh.banking.models.Transaction;
import com.estaytieh.banking.response.AbstractResource;
import com.estaytieh.banking.services.AccountService;
import com.estaytieh.banking.services.TransactionService;
import com.estaytieh.banking.services.UserInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("api/accounts")
public class AccountController
    extends AbstractResource
{

  private static final Logger LOGGER = LoggerFactory.getLogger(AccountController.class);

  private static final String NO_ACCOUNT_FOUND =
      "Unable to find an account matching this sort code and account number";

  private static final String USER_NOT_FOUND =
      "Unable to find user by customerId to create new account";

  private static final String ACCOUNT_CREATION_FAILED =
      "Unable to create new ACcount";
  private static final String TRANSACTION_FAILED =
      "failed to create transaction";

  private static final String SUCCESS =
      "New Account created Successfully";

  private final AccountService accountService;
  private final TransactionService transactionService;
  private final UserInfoService userInfoService;

  @Autowired
  private CreateAccountForm createAccountForm;

  @ModelAttribute("createAccountForm")
  public CreateAccountForm getCreateAccountForm() {
    return createAccountForm;
  }

  @Autowired
  public AccountController(AccountService accountService,TransactionService transactionService,UserInfoService userInfoService) {
    this.accountService = accountService;
    this.transactionService = transactionService;
    this.userInfoService = userInfoService;
  }

  @GetMapping(value = "/allAccounts", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> getAllAccounts()
  {
    LOGGER.debug("Triggered AccountController.accountInput");

    List<Account> accounts = accountService.getAllAccounts();
    List<Transaction> transactions = transactionService.getAllTransactions();
    combineAccountsWithTransctions(accounts,transactions);

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



  @RequestMapping(path = "/createNewAccount", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> createNewUserAccount(@RequestParam String customerId,@RequestParam String initialCredit)
  {
    LOGGER.debug("Triggered AccountController.accountInput");
    String result = createNewAccount(customerId,Double.parseDouble(initialCredit));
    return new ResponseEntity<>(result, HttpStatus.OK);

  }
//  @PostMapping("/addAccount")
//  public String createNewAccount(@ModelAttribute("createAccountForm") CreateAccountForm createAccountForm,
//                                      Model model) {
//    String iban = createAccountForm.getCustomerId();
//    double sum = createAccountForm.getInitialCredit();
//
//
//      model.addAttribute("successfulAccountCreation", "successfulAccountCreation");
//
//
//    return "accounts";
//  }


  @RequestMapping(value = "/addAccount", method = RequestMethod.POST)
  public String addAccount(@RequestParam Map<String,String> requestParams)
      throws Exception
  {
    String email=requestParams.get("customerId");
    String username=requestParams.get("initialCredit");

    System.out.println("Email : " + email +
        "\n Username :" + username);
    // your code logic
    return "success";
  }


//  @RequestMapping(path="/addAccount2", produces=MediaType.TEXT_PLAIN_VALUE)
//  @ResponseBody
//  public String processForm(@RequestParam String customerId,
//                            @RequestParam(required=false) String initialCredit) {
//
//
//    System.out.println("customerId : " + customerId +
//        "\n initialCredit :" + initialCredit);
//
//    return "success";
//  }

//
//  @RequestMapping(value = "/saveData", headers="Content-Type=application/json", method = RequestMethod.POST)
//  @ResponseBody
//  public ResponseEntity<?> saveData(HttpServletRequest request){
//    String jsonString = request.getParameter("json");
//    System.out.println("jsonString"+jsonString);
//    return new ResponseEntity<>(NO_ACCOUNT_FOUND, HttpStatus.NO_CONTENT);
//
//  }


//  @PostMapping(value = "/createAccount",
//      consumes = MediaType.APPLICATION_JSON_VALUE,
//      produces = MediaType.APPLICATION_JSON_VALUE)
  @PostMapping(value = "/createAccount")
  public ResponseEntity<?> createAccount(
      @Valid @RequestBody AccountCreationInput accountCreationInput) {

      String customerId = accountCreationInput.getCustomerId();
      String result = createNewAccount(accountCreationInput.getCustomerId(),accountCreationInput.getInitialCredit());
      if(result.equals(SUCCESS))
        return SUCCESS_NO_DATA;
      else
        return new ResponseEntity<>(result, HttpStatus.NOT_ACCEPTABLE);

  }

  private String createNewAccount(String customerId,Double initialCredit)
  {
    String result = SUCCESS;
    LOGGER.debug("Triggered createNewAccount");
    Long userId= userInfoService.getUserId(customerId);
    boolean isComplete = false;
    if (userId == null) {
      return USER_NOT_FOUND;
    } else {
      Long accountId = accountService.createNewAccount(userId,initialCredit);
      if (accountId == null)
        return ACCOUNT_CREATION_FAILED;
      else
        isComplete = transactionService.createTransactionForNewAccount(accountId,initialCredit);

      if (!isComplete )
        return TRANSACTION_FAILED;
      else
        return SUCCESS;
    }
  }

  private List<Account> combineAccountsWithTransctions( List<Account> accounts,List<Transaction> transactions)
  {
    accounts.stream()
        .forEach(account -> transactions.stream()
            .filter(transaction -> transaction.getAccountId() == account.getId())
            .forEach(transaction -> {
              if (account.getTransactions() == null)
                account.setTransactions(new ArrayList<>());
              account.getTransactions().add(transaction);
            }));
    return accounts;
  }


}
