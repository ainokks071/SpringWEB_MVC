create table member_auth(
num int not null auto_increment,
memID varchar(50) not null, -- FK
auth varchar(50) not null,
primary key(num),
foreign key(memID) references member(memID)
);

drop table member_auth;

-- 참조키(memID) -> member테이블의 memID 참조.
--constraint fk_member_auth foreign key(memID) references member(memID)