package com.estaytieh.banking.repositories;

import com.estaytieh.banking.models.Account;
import com.estaytieh.banking.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TransactionRepository
    extends JpaRepository<Transaction, Long> {

  //  @Query("SELECT a FROM Account a WHERE a.accountNumber = ?1 ")
  Optional<Transaction> findTransactionByAccountId(Long accountId);
}