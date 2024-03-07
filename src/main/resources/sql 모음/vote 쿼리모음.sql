/*투표대상날짜 생성*/
insert into vote(
	vote_id, vote_date
)
values(
	0,'2024-03-10 00:00:00.000'
);

/*시퀀스 1로 초기화*/
alter table vote auto_increment = 1;

/*전체 조회*/
select * from vote;

/*날짜로 조건부 조회*/
select *
from vote
where VOTE_DATE = '2024-03-10 00:00:00.000';

/*여러개 삭제*/
delete from vote where vote_id in(12);

/*한개 삭제*/
delete from vote 
where vote_date = '2024-03-10 00:00:00.000';

/*특정조건으로 카운트*/
select count(*)
from vote
where VOTE_DATE = '2024-03-10 00:00:00.000';




