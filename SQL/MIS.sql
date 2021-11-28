create table id_userKey_jurisdiction
(
	id char(16) primary key,
	userKey char(32),
	jurisdiction char(40)
);

alter table id_userKey_jurisdiction alter column id char(16);

insert into id_userKey_jurisdiction values('0', 'cfcd208495d565ef66e7dff9f98764da', '队长');
insert into id_userKey_jurisdiction values('4798755368032503', '89ff326a148052b31e2e36056ba094c0', '队长');
insert into id_userKey_jurisdiction values('3', 'eccbc87e4b5ce2fe28308fd9f2a7baf3', '普通成员');
insert into id_userKey_jurisdiction values('2', 'c81e728d9d4c2f636f067f89cc14862c', '精英');
insert into id_userKey_jurisdiction values('1', 'c4ca4238a0b923820dcc509a6f75849b', '副队长');
insert into id_userKey_jurisdiction values('4', 'a87ff679a2f3e71d9181a67b7542122c', '车队新人');
insert into id_userKey_jurisdiction values('5', 'e4da3b7fbbce2345d7772b0674a318d5', '副队长');

select * from id_userKey_jurisdiction where jurisdiction in ('队长', '副队长', '精英', '普通成员', '车队新人') order by charindex(rtrim(cast(jurisdiction as nchar)), (', 队长, 副队长, 精英, 普通成员, 车队新人, '));
select * from id_userKey_jurisdiction where id = '1';

create table weekContains20210524
(
	Pname varchar(20) constraint c1 not null,
	Id char(16) constraint c2 not null,
	Sex char(4),
	ActivityLastWeek smallint,
	TotalPointsAsOfLastWeek int,
	TotalPointsOfLastWeek smallint,
	IncreaseOfIntegral smallint,
	GPA numeric(18, 2),
	Prange smallint,
	Post varchar(8),
	primary key(Pname, Id),
	--foreign key(Id) references id_userKey_jurisdiction(id)
);
alter table weekContains20210524 alter column Id char(16);

select * from weekContains20210524 where Post in ('队长', '副队长', '精英', '普通成员', '车队新人') order by (charindex(rtrim(cast(Post as nchar)), (', 队长, 副队长, 精英, 普通成员, 车队新人, '))), GPA desc;

drop table weekContains20210524;

insert into weekContains20210524 values
--('第一楼づ银狼', '4798755368032503', '女', 910, 215, 258, 43, 723.00, 3, '队长'),
--('北镇猫少女', '4798755368032504', '女', 496, 0, 0, 0, 347.20, 9, '精英'),
--('@时时念念', '4798755368032505', '女', 668, 0, 0, 0, 467.60, 8, '精英')

update weekContains20210524 set Pname = '第一楼づ银狼', Id = '4798755368032503', Sex = '女', ActivityLastWeek = 910, TotalPointsAsOfLastWeek = 215, TotalPointsOfLastWeek = 258, IncreaseOfIntegral = 43, GPA = 723.00, Prange = 3, Post = '队长' where Id = '4798755368032503';

insert into id_userKey_jurisdiction values('4798755368032506', '310b7261733c71f1f52d765b1faa5e0d', '车队新人');
insert into weekContains20210524 values('dog达', '4798755368032506', '男', 0, 0, 0, 0, 0, 10, '车队新人');

delete from weekContains20210524 where Id = '4798755368032504';

select * from weekContains20210524 where Id = '4798755368032504';

select Id, Pname, Sex, ActivityLastWeek, TotalPointsAsOfLastWeek, TotalPointsOfLastWeek, GPA, Post from weekContains20210524 where Id like '%0%' or Pname like '%0%' or Sex like '%0%'or ActivityLastWeek like '%0%' or TotalPointsAsOfLastWeek like '%0%' or TotalPointsOfLastWeek like '%0%' or GPA like '%0%' or Post like '%0%' order by (charindex(rtrim(cast(Post as nchar)), (', 队长, 副队长, 精英, 普通成员, 车队新人, '))), GPA desc;