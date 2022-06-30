package com.estaytieh.banking.controllers;

//import com.example.paul.models.Account;
//import com.example.paul.services.AccountService;
//import com.example.paul.utils.AccountInput;
//import com.example.paul.utils.InputValidator;
import com.estaytieh.banking.forms.CreateAccountForm;
import com.estaytieh.banking.inputs.AccountCreationInput;
import com.estaytieh.banking.models.Account;
import com.estaytieh.banking.models.Transaction;
import com.estaytieh.banking.models.Userinfo;
import com.estaytieh.banking.services.AccountService;
import com.estaytieh.banking.services.TransactionService;
import com.estaytieh.banking.services.UserInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
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

  @RequestMapping(path = "/createNewAccount", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> createNewAccount(@RequestParam String customerId,@RequestParam String initialCredit)
  {
    LOGGER.debug("Triggered AccountController.accountInput");
    Userinfo userinfo = userInfoService.getUserInfo(customerId);
    if (userinfo == null) {
      return new ResponseEntity<>(NO_ACCOUNT_FOUND, HttpStatus.NO_CONTENT);
    } else {
      Double credit = 0.0;
      if (initialCredit != null)
        credit= Double.parseDouble(initialCredit);
      Account account = new Account();
      account.setUserId(userinfo.getId());
      account.setCurrentBalance(credit);
      account = accountService.saveAccount(account);



      return new ResponseEntity<>(account, HttpStatus.OK);
    }

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
  public String createNewAccount(@RequestParam Map<String,String> requestParams)
      throws Exception
  {
    String email=requestParams.get("customerId");
    String username=requestParams.get("initialCredit");

    System.out.println("Email : " + email +
        "\n Username :" + username);
    // your code logic
    return "success";
  }


  @RequestMapping(path="/addAccount2", produces=MediaType.TEXT_PLAIN_VALUE)
  @ResponseBody
  public String processForm(@RequestParam String customerId,
                            @RequestParam(required=false) String initialCredit) {


    System.out.println("customerId : " + customerId +
        "\n initialCredit :" + initialCredit);

    return "success";
  }


  @RequestMapping(value = "/saveData", headers="Content-Type=application/json", method = RequestMethod.POST)
  @ResponseBody
  public ResponseEntity<?> saveData(HttpServletRequest request){
    String jsonString = request.getParameter("json");
    System.out.println("jsonString"+jsonString);
    return new ResponseEntity<>(NO_ACCOUNT_FOUND, HttpStatus.NO_CONTENT);

  }


  @PostMapping(value = "/createAcount",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> createNewAccount(
      @Valid @RequestBody AccountCreationInput accountCreationInput) {
//    if (InputValidator.isSearchTransactionValid(accountCreationInput)) {
//            new Thread(() -> transactionService.makeTransfer(transactionInput));
//      boolean isComplete = transactionService.makeTransfer(transactionInput);
      String customerId = accountCreationInput.getCustomerId();
      boolean isComplete = true;
      return new ResponseEntity<>(isComplete, HttpStatus.OK);
//    } else {
//      return new ResponseEntity<>(NO_ACCOUNT_FOUND, HttpStatus.BAD_REQUEST);
//    }
  }


}
