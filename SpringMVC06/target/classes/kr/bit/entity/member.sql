create table member(
memIdx int not null, 
memID varchar(20) not null, -- PK
memPassword varchar(68) not null,
memName varchar(20) not null,
memAge int,
memGender varchar(20),
memEmail varchar(50),
memProfile varchar(50),
primary key(memID)
);

drop table member;