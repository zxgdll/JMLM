truncate table user;
insert into user
select * from user_last_bak;
commit;

update user set user_name = '于景威' where user_id = 3;
delete from user where user_name like '于景威（%';

update user set user_name = '李云英' where user_id = 7;
delete from user where user_name like '李云英（%';

delete from user where user_name like '齐建（%';

update user set user_name = '万菲菲' where user_id = 12;
delete from user where user_name like '万菲菲（%';

update user set user_name = '张玉文' where user_id = 15;
delete from user where user_name like '张玉文（%';

update user set user_name = '王凤枝' where user_id = 18;
delete from user where user_name like '王凤枝（%';

update user set user_name = '冯彩霞' where user_id = 21;
delete from user where user_name like '冯彩霞（%';

update user set user_name = '郭燕' where user_id = 24;
delete from user where user_name like '郭燕（%';

update user set user_name = '齐春花' where user_id = 27;
delete from user where user_name like '齐春花（%';

update user set user_name = '吴奎' where user_id = 33;
delete from user where user_name like '吴奎（%';

update user set user_name = '韩乌仁沙娜' where user_id = 94;
delete from user where user_name like '韩乌仁沙娜（%';

update user set user_name = '乌云其木格' where user_id = 191;
delete from user where user_name like '乌云其木格（%';

update user set user_name = '荆秀平' where user_id = 34;
delete from user where user_name like '荆秀平（%';

update user set user_name = '李翠香' where user_id = 37;
delete from user where user_name like '李翠香（%';

update user set user_name = '刘春妙' where user_id = 95;
delete from user where user_name like '刘春妙（%';
commit;
select * from user;