package com.estaytieh.banking.inputs;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;

@Data
public class AccountCreationInput {

//  @NotEmpty
//  @Length(min = 20, max = 22)
  private String customerId;

//  @NotEmpty
//  @DecimalMin("0.00")
//  @DecimalMax("99999999999.00")
//  @Positive(message = "Credit must be positive")
//  @Min(value = 0, message = "Amount must be larger than 0")
  private double initialCredit;
}
