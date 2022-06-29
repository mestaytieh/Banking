INSERT INTO bank.usertype (id,usertype)
values (1, 'Admin');
INSERT INTO bank.usertype (id,usertype)
values (2, 'Employee');
INSERT INTO bank.usertype (id,usertype)
values (3, 'Customer');

INSERT INTO bank.user (id,username,fullname,usertype,passwordhash,pepper,salt,bankname)
values (1,'jane.doe','Jane Doe','Admin','3asd54q415as7d6ref51as65dj12oiaskd4','asd64a5das5d46a5s4d6a5as6d54','39123*/)123a+','World Bank');
INSERT INTO bank.user (id,username,fullname,usertype,passwordhash,pepper,salt,bankname)
values (2,'john.doe','John Doe','Employee','3496rjlskdtup04jffdsguy328ruaskdjfl','ldjuhrlgds8yh5ejblak2344tki234','39123*/)123a+','World Bank');
INSERT INTO bank.user (id,username,fullname,usertype,passwordhash,pepper,salt,bankname)
values (3,'batman','Bruce Wayne','Customer','dseli56u3i82rfklfsaog98u5erasdf25aewt45t','sarglaeu4thladsufeakrlifksaf','39123*/)123a+','World Bank');
INSERT INTO bank.user (id,username,fullname,usertype,passwordhash,pepper,salt,bankname)
values (4,'mestaytieh','Mohammad Estaytieh','Customer','afleaur4th3i4uthrkseu754szyfgresu47f','2rkiwaeyytaki34uyf7ytkzgudr','39123*/)123a+','World Bank');

INSERT INTO bank.account (id, accountnumber, currentbalance, bankname, userid)
VALUES (1,  '12345678', 1119963071.69, 'World Bank', 3);
INSERT INTO bank.account (id, sort_code, account_number, current_balance, bank_name, owner_name)
VALUES (2,  '11122233', 3652.78, 'World Bank', 4);

INSERT INTO bank.transaction (id, sourceaccountid, targetaccountid,amount, initiationdate, completiondate, transactionreason)
VALUES (1, 4, 3, 1000000.00, '2022-06-27 09:15', null, 'buying mansion');
