package com.estaytieh.banking.repositories;

import com.estaytieh.banking.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository
  extends JpaRepository<Account, Long> {

//  @Query("SELECT a FROM Account a WHERE a.accountNumber = ?1 ")
  Optional<Account> findByAccountNumber(String accountNumber);

  List<Account> findAccountsByUserId(Long userId);

}

