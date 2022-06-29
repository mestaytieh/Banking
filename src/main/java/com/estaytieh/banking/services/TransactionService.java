package com.estaytieh.banking.services;

import com.estaytieh.banking.models.Account;
import com.estaytieh.banking.models.Transaction;
import com.estaytieh.banking.repositories.AccountRepository;
import com.estaytieh.banking.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


  public boolean createTransaction(Transaction transaction) {
    transactionRepository.save(transaction);
    return true;
  }
}
