package com.estaytieh.banking.services;

import com.estaytieh.banking.models.Account;
import com.estaytieh.banking.models.Userinfo;
import com.estaytieh.banking.repositories.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserInfoService
{
  @Autowired
  private UserInfoRepository userInfoRepository;

  public Userinfo getUserInfo(String userName) {
    Optional<Userinfo> userinfo = userInfoRepository
        .findUserinfoByUserName( userName);

    return userinfo.orElse(null);
  }

  public Long getUserId(String userName) {
    Userinfo userinfo = getUserInfo(userName);
    return userinfo != null ? userinfo.getId() : null;

  }

  public Userinfo getUserById(Long Id) {
    Optional<Userinfo> userinfo = userInfoRepository
        .findById(Id);

    return userinfo.orElse(null);
  }

}
