create table id_userKey_jurisdiction
(
	id char(16) primary key,
	userKey char(32),
	jurisdiction char(40)
);

alter table id_userKey_jurisdiction alter column id char(16);

insert into id_userKey_jurisdiction values('0', 'cfcd208495d565ef66e7dff9f98764da', '�ӳ�');
insert into id_userKey_jurisdiction values('4798755368032503', '89ff326a148052b31e2e36056ba094c0', '�ӳ�');
insert into id_userKey_jurisdiction values('3', 'eccbc87e4b5ce2fe28308fd9f2a7baf3', '��ͨ��Ա');
insert into id_userKey_jurisdiction values('2', 'c81e728d9d4c2f636f067f89cc14862c', '��Ӣ');
insert into id_userKey_jurisdiction values('1', 'c4ca4238a0b923820dcc509a6f75849b', '���ӳ�');
insert into id_userKey_jurisdiction values('4', 'a87ff679a2f3e71d9181a67b7542122c', '��������');
insert into id_userKey_jurisdiction values('5', 'e4da3b7fbbce2345d7772b0674a318d5', '���ӳ�');

select * from id_userKey_jurisdiction where jurisdiction in ('�ӳ�', '���ӳ�', '��Ӣ', '��ͨ��Ա', '��������') order by charindex(rtrim(cast(jurisdiction as nchar)), (', �ӳ�, ���ӳ�, ��Ӣ, ��ͨ��Ա, ��������, '));
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

select * from weekContains20210524 where Post in ('�ӳ�', '���ӳ�', '��Ӣ', '��ͨ��Ա', '��������') order by (charindex(rtrim(cast(Post as nchar)), (', �ӳ�, ���ӳ�, ��Ӣ, ��ͨ��Ա, ��������, '))), GPA desc;

drop table weekContains20210524;

insert into weekContains20210524 values
--('��һ¥������', '4798755368032503', 'Ů', 910, 215, 258, 43, 723.00, 3, '�ӳ�'),
--('����è��Ů', '4798755368032504', 'Ů', 496, 0, 0, 0, 347.20, 9, '��Ӣ'),
--('@ʱʱ����', '4798755368032505', 'Ů', 668, 0, 0, 0, 467.60, 8, '��Ӣ')

update weekContains20210524 set Pname = '��һ¥������', Id = '4798755368032503', Sex = 'Ů', ActivityLastWeek = 910, TotalPointsAsOfLastWeek = 215, TotalPointsOfLastWeek = 258, IncreaseOfIntegral = 43, GPA = 723.00, Prange = 3, Post = '�ӳ�' where Id = '4798755368032503';

insert into id_userKey_jurisdiction values('4798755368032506', '310b7261733c71f1f52d765b1faa5e0d', '��������');
insert into weekContains20210524 values('dog��', '4798755368032506', '��', 0, 0, 0, 0, 0, 10, '��������');

delete from weekContains20210524 where Id = '4798755368032504';

select * from weekContains20210524 where Id = '4798755368032504';

select Id, Pname, Sex, ActivityLastWeek, TotalPointsAsOfLastWeek, TotalPointsOfLastWeek, GPA, Post from weekContains20210524 where Id like '%0%' or Pname like '%0%' or Sex like '%0%'or ActivityLastWeek like '%0%' or TotalPointsAsOfLastWeek like '%0%' or TotalPointsOfLastWeek like '%0%' or GPA like '%0%' or Post like '%0%' order by (charindex(rtrim(cast(Post as nchar)), (', �ӳ�, ���ӳ�, ��Ӣ, ��ͨ��Ա, ��������, '))), GPA desc;