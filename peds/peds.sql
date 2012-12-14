create table peds_runparams(code varchar2(50),value varchar2(100),desc varchar(200));--创建参数表
create sequence seq_peds_file start with 1 increment by 1 no cycle;--文件表主键序列
create sequence seq_peds_user start with 1 increment by 1 no cycle;--用户表主键序列
create table peds_user(id int,name varchar(100),password varchar(500),pinyin varchar(100),
pwdupdatetime varchar(100),faillogin int,lockflag int,role int);

