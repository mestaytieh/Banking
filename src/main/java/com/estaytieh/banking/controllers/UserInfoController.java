package com.estaytieh.banking.controllers;

import com.estaytieh.banking.models.Userinfo;
import com.estaytieh.banking.services.UserInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/userinfo")
public class UserInfoController {

  private static final Logger LOGGER = LoggerFactory.getLogger(UserInfoController.class);
  private static final String USER_NOT_FOUND =
      "Unable to find the user requested";

  private final UserInfoService userInfoService;

  public UserInfoController(UserInfoService userInfoService) {
    this.userInfoService = userInfoService;
  }

  @RequestMapping(path = "/userbyCustomerId/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> getUserByCustomerId(@PathVariable String id)
  {
    LOGGER.debug("Triggered AccountController.accountInput");

    Userinfo user = userInfoService.getUserInfo(id);

//
//      // Return the account details, or warn that no account was found for given input
    if (user == null) {
      return new ResponseEntity<>(USER_NOT_FOUND, HttpStatus.NO_CONTENT);
    } else {
      return new ResponseEntity<>(user, HttpStatus.OK);
    }

  }


}
