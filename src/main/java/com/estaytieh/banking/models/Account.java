package com.estaytieh.banking.models;

import lombok.Data;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "account", schema = "bank")
@Data
public class Account
    implements Serializable
{

  @Id
  private long id;
  private String accountNumber;
  private double currentBalance;
  private String bankName;

  private transient List<Transaction> transactionList;
}