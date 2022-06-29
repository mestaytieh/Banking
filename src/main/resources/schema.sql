CREATE SCHEMA bank;

CREATE TABLE bank.usertype (
    id bigint NOT NULL PRIMARY KEY,
    usertype CHAR(100) NOT NULL,
    UNIQUE (id, usertype)
);

CREATE TABLE bank.Address (
    id bigint NOT NULL PRIMARY KEY,
    country varchar(100) not null,
    province varchar(100) not null,
    state varchar(100) not null,
    postalcode VARCHAR (20) not null,
    city varchar (100) not null,
    address varchar (400) not null
);

CREATE TABLE bank.user (
    id bigint NOT NULL PRIMARY KEY,
    username CHAR(100) NOT NULL,
    fullname CHAR(200) NOT NULL,
    usertype CHAR(100) NOT NULL REFERENCES bank.usertype(usertype)
    passwordHash CHAR(200) NOT NULL,
    pepper CHAR(50) NOT NULL,
    salt CHAR (100) NOT NULL,
    bankname VARCHAR(50) NOT NULL,
    email VARCHAR (200),
    phone VARCHAR (30),
    address bigint REFERENCES bank.address(id)
    UNIQUE (username)
);

CREATE TABLE bank.account (
    id bigint NOT NULL PRIMARY KEY,
    accountnumber CHAR(10) NOT NULL,
    currentbalance NUMERIC(10,3) NOT NULL,
    bankname VARCHAR(50) NOT NULL,
    userid bigint not null REFERENCES bank.user(id),
    UNIQUE (accountnumber)
);

CREATE SEQUENCE bank.transaction_sequence START WITH 2;
CREATE TABLE bank.transaction (
    id bigint NOT NULL PRIMARY KEY,
    sourceaccountid bigint REFERENCES bank.account(id),
    targetaccountid bigint REFERENCES bank.account(id),
    amount NUMERIC(10,3) NOT NULL,
    initiationdate timestamp NOT NULL,
    completiondate TIMESTAMP,
    transactionreason VARCHAR(255)
);