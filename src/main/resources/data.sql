INSERT INTO bank.usertype (id,usertype)
values (1, 'Admin');
INSERT INTO bank.usertype (id,usertype)
values (2, 'Employee');
INSERT INTO bank.usertype (id,usertype)
values (3, 'Customer');

INSERT INTO bank.userinfo (id,user_name,first_name,sur_name,user_type,password_hash,pepper,salt,bank_name)
values (1,'jane.doe','Jane','Doe','Admin','3asd54q415as7d6ref51as65dj12oiaskd4','asd64a5das5d46a5s4d6a5as6d54','39123*/)123a+','World Bank');
INSERT INTO bank.userinfo (id,user_name,first_name,sur_name,user_type,password_hash,pepper,salt,bank_name)
values (2,'john.doe','John','Doe','Employee','3496rjlskdtup04jffdsguy328ruaskdjfl','ldjuhrlgds8yh5ejblak2344tki234','39123*/)123a+','World Bank');
INSERT INTO bank.userinfo (id,user_name,first_name,sur_name,user_type,password_hash,pepper,salt,bank_name)
values (3,'batman','Bruce','Wayne','Customer','dseli56u3i82rfklfsaog98u5erasdf25aewt45t','sarglaeu4thladsufeakrlifksaf','39123*/)123a+','World Bank');
INSERT INTO bank.userinfo (id,user_name,first_name,sur_name,user_type,password_hash,pepper,salt,bank_name)
values (4,'mestaytieh','Mohammad','Estaytieh','Customer','afleaur4th3i4uthrkseu754szyfgresu47f','2rkiwaeyytaki34uyf7ytkzgudr','39123*/)123a+','World Bank');

INSERT INTO bank.account (id, account_number, current_balance, bank_name, user_id,iban)
VALUES (1,  '12345678', 1119963071.69, 'World Bank', 3,'WB111321321');
INSERT INTO bank.account (id,  account_number, current_balance, bank_name, user_id,iban)
VALUES (2,  '11122233', 3652.78, 'World Bank', 4,'WB111321322');


INSERT INTO bank.transaction (id, account_id, iban,amount, creation_date, transaction_reason)
VALUES (1, 2, 'WB111321321', 1000000.00, '2022-06-27 09:15', 'buying mansion');
