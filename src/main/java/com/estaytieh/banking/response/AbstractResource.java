package com.estaytieh.banking.response;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public abstract class AbstractResource {

  private static final Success SUCCESS = new Success();
  protected static final ResponseEntity<Success> SUCCESS_NO_DATA = new ResponseEntity<>(SUCCESS, HttpStatus.OK);

  protected AbstractResource() {
    // empty constructor
  }

  @Data
  private static class Success {
    private boolean success = true;
  }

}
