package com.estaytieh.banking.models;

import lombok.Data;


import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "account", schema = "bank")
@Data
public class Account
    implements Serializable
{

  @Id
//  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  private String accountNumber;
  private String iban;
  private double currentBalance;
  private String bankName;
  @Column(name = "user_id")
  private long userId;


  @Transient
  private List<Transaction> transactions;
}