package com.estaytieh.banking.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "userinfo", schema = "bank")
@Data
public class Userinfo
    implements Serializable
{
  @Id
  private long id;
  private String userName;
  private String firstName;
  private String surName;
  private String userType;
  private String passwordHash;
  private String pepper;
  private String salt;
  private String bankName;
  private String email;
  private String phone;
  private String address;

  private transient List<Account> accountList;

}