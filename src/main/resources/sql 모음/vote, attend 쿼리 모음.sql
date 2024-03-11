select * from vote;

/*test data insert*/
insert into attend(VOTE_ID, FB_USER_ID , ATTEND_STATUS) values(12, 1, 'F');
insert into attend(VOTE_ID, FB_USER_ID , ATTEND_STATUS) values(12, 2, 'N');
insert into attend(VOTE_ID, FB_USER_ID , ATTEND_STATUS) values(12, 3, 'N');
insert into attend(VOTE_ID, FB_USER_ID , ATTEND_STATUS) values(12, 24, 'Y');
insert into attend(VOTE_ID, FB_USER_ID , ATTEND_STATUS) values(12, 25, 'Y');

/*test vote data delete*/
delete from attend where VOTE_ID = 10;
delete from vote where VOTE_ID = 10;

/*참석데이터 조회*/
select * from attend a ;

/*유저에 따른 참석데이터 조회*/
select *
from attend
where FB_USER_ID = 25;

/*참석인원 조회*/
select fb_user_id
from attend
where ATTEND_STATUS = 'Y';

/*투표id로 투표대상날짜 조회*/
select VOTE_DATE  
from vote
where 1=1
and VOTE_ID = 10;

/*유저찾기*/
select * from fb_user fu ;
