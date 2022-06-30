package com.estaytieh.banking.forms;


import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Component;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;

@Component
@Data
public class CreateAccountForm {

  @NotEmpty
  @Length(min = 20, max = 22)
  private String customerId;

  @NotEmpty
  @DecimalMin("0.00")
  @DecimalMax("99999999999.00")
  private double initialCredit;
;
}
