CREATE SCHEMA bank;

CREATE TABLE bank.usertype (
    id bigint NOT NULL PRIMARY KEY,
    usertype varchar(100) NOT NULL
);

CREATE TABLE bank.address (
    id bigint NOT NULL PRIMARY KEY,
    country varchar(100) not null,
    province varchar(100) not null,
    state varchar(100) not null,
    postalcode VARCHAR (20) not null,
    city varchar (100) not null,
    address varchar (400) not null
);

CREATE TABLE bank.userinfo (
    id bigint NOT NULL PRIMARY KEY,
    username varchar(100) NOT NULL,
    firstname varchar(200) NOT NULL,
    surname varchar(200) NOT NULL,
    usertype varchar(100) NOT NULL,
    passwordHash varchar(200) NOT NULL,
    pepper varchar(50) NOT NULL,
    salt varchar (100) NOT NULL,
    bankname VARCHAR(50) NOT NULL,
    email VARCHAR (200),
    phone VARCHAR (30),
    address bigint REFERENCES bank.address(id),
    UNIQUE (username,bankname)
);

CREATE TABLE bank.account (
    id bigint NOT NULL PRIMARY KEY,
    account_number varchar(10) NOT NULL,
    iban VARCHAR(100),
    current_balance NUMERIC(100,3) NOT NULL,
    bank_name VARCHAR(50) NOT NULL,
    user_id bigint not null REFERENCES bank.userinfo(id),
    UNIQUE (account_number)
);

CREATE SEQUENCE bank.transaction_sequence START WITH 2;

CREATE TABLE bank.transaction (
    id bigint NOT NULL PRIMARY KEY,
    account_id bigint REFERENCES bank.account(id),
    iban VARCHAR(100),
    amount NUMERIC(100,3) NOT NULL,
    creation_date timestamp NOT NULL,
    transaction_reason VARCHAR(500)
);