CREATE SCHEMA bank;

CREATE TABLE bank.usertype (
    id bigint NOT NULL PRIMARY KEY,
    usertype CHAR(100) NOT NULL,
    UNIQUE (id, usertype)
);

CREATE TABLE bank.user (
    id bigint NOT NULL PRIMARY KEY,
    username CHAR(100) NOT NULL,
    fullname CHAR(200) NOT NULL,
    usertype CHAR(100) NOT NULL REFERENCES bank.usertype(usertype)
    passwordHash CHAR(200) NOT NULL,
    pepper CHAR(50) NOT NULL,
    salt CHAR (50) NOT NULL,
    bankname VARCHAR(50) NOT NULL,
    UNIQUE (username)
);

CREATE TABLE bank.account (
    id bigint NOT NULL PRIMARY KEY,
    accountnumber CHAR(10) NOT NULL,
    currentbalance NUMERIC(10,3) NOT NULL,
    bankname VARCHAR(50) NOT NULL,
    UNIQUE (accountnumber)
);

CREATE SEQUENCE bank.transaction_sequence START WITH 1;
CREATE TABLE bank.transaction (
    id bigint NOT NULL PRIMARY KEY,
    sourceaccountid bigint REFERENCES bank.account(id),
    targetaccountid bigint REFERENCES bank.account(id),
    amount NUMERIC(10,3) NOT NULL,
    initiationdate timestamp NOT NULL,
    completiondate TIMESTAMP,
    transactionreason VARCHAR(255)
);