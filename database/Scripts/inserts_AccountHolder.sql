use BATS_DB;
insert into Cardtbl values('0223584386674869','487','2015-11-22 12;35:40',0,'436978');
insert into Accountholdertbl values('1234567890123','Mark Shualts','Four ways crossing','0545678432','0223584386674869');
insert into Accounttbl values('23585678310','Savings Account',10000.00,1000.00,5000.00,0,'0223584386674869');
insert into Transactiontbl values(1,2016-12-09 12:36:45,2000.00,'Transfer',56,'23585678310');
insert into ATMtbl values(56,190000.00);

-- -----------------------------------------------------------------------------------------------------------------------

insert into Cardtbl values('0123587014566667','657','2015-11-22 12:35:40',0,'436978');
insert into AccoutnHoldertbl values('9711015029085','Michael Smith','2 Jatinga Close','0765678954','0123587014566667');
insert into Accounttbl values('23584234969','Current Account',100000.00,100.00,50000.00,1,'0123587014566667');
insert into Transactiontbl values(2,'2016-04-24 08:45:08',3000.00,'Withdrawal',10,'23584234969');
insert into ATMtbl values(10,190000.00);
insert into ATMtbl values(20,190000.00);
insert into Transactiontbl values(3,'2016-05-06 16:05:58',400.00,'Deposit',20,'23584234969');
