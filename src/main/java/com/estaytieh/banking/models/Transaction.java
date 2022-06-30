package com.estaytieh.banking.models;

import lombok.Data;
import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.*;

@Entity
@Table(name = "transaction", schema = "bank")
@Data
@SequenceGenerator(name = "transaction_seq", sequenceName = "transaction_sequence", schema = "bank", initialValue = 2)
public class Transaction
    implements Serializable
{

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transaction_seq")
  private long id;
  private long accountId;
  private String iban;
  private Double amount;
  private Timestamp creationDate;
  private String transactionReason;


}
