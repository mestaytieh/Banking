package com.estaytieh.banking.services;

import com.estaytieh.banking.models.Account;
import com.estaytieh.banking.models.Transaction;
import com.estaytieh.banking.repositories.AccountRepository;
import com.estaytieh.banking.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {


  @Autowired
  private TransactionRepository transactionRepository;


  public List<Transaction> getAllTransactions()
  {
    return transactionRepository.findAll();
  }

  public List<Transaction> getAllTransactionsByAccountId(long accountId)
  {
    return transactionRepository.findTransactionByAccountId(accountId);
  }

  public boolean createTransaction(Transaction transaction) {
    transactionRepository.save(transaction);
    return true;
  }

  public boolean createTransactionForNewAccount(Long accountId, Double amount) {
    if (amount != null && amount > 0) {
      Transaction newtransaction = new Transaction();
      newtransaction.setAccountId(accountId);
      newtransaction.setAmount(amount);
      newtransaction.setCreationDate(new Timestamp(System.currentTimeMillis()));
      newtransaction.setTransactionReason("Opening Account");
      return createTransaction(newtransaction);
    }
    else
      return true;
  }
}
