package com.estaytieh.banking.models;

import lombok.Data;


import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "account", schema = "bank")
@Data
@SequenceGenerator(name = "account_sequence", sequenceName = "account_sequence", schema = "bank", initialValue = 3)
public class Account
    implements Serializable
{

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "account_sequence")
  private long id;
  
  private String accountNumber;
  private String iban;
  private Double currentBalance;
  private String bankName;
  @Column(name = "user_id")
  private long userId;


  @Transient
  private List<Transaction> transactions;
}