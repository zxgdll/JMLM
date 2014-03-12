truncate table user_bak;
insert into user_bak select * from user;
commit;
truncate table user;
insert into user select * from user_bak where user_id <= 186;
commit;
select * from user;