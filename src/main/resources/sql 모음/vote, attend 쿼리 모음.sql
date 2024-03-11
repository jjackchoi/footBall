select * from vote;

/*test data insert*/
insert into attend(VOTE_ID, FB_USER_ID , ATTEND_STATUS) values(14, 1, 'F');
insert into attend(VOTE_ID, FB_USER_ID , ATTEND_STATUS) values(14, 2, 'N');
insert into attend(VOTE_ID, FB_USER_ID , ATTEND_STATUS) values(14, 3, 'N');
insert into attend(VOTE_ID, FB_USER_ID , ATTEND_STATUS) values(14, 24, 'Y');
insert into attend(VOTE_ID, FB_USER_ID , ATTEND_STATUS) values(14, 25, 'Y');

/*test vote data delete*/
delete from attend where VOTE_ID = 12;
delete from vote where VOTE_ID = 13;

/*참석데이터 조회*/
select FB_USER_ID  from attend a ;

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

/*조인하여 fb_user와 attend테이블에서 fb_user_id에 대한 차집합 조회*/ 
select fu.FB_USER_ID 
from fb_user fu
left join (
	select *
	from attend
	where VOTE_ID = (
		select vote_id
		from vote
		where VOTE_DATE = '2024-03-17 00:00:00.000'
		)
) a
on fu.FB_USER_ID = a.FB_USER_ID 
where a.FB_USER_ID is null
or fu.FB_USER_ID is null;

/*특정 날짜조건에 따른 attend테이블 조회*/
select *
from attend
where VOTE_ID = (select vote_id
from vote
where VOTE_DATE = '2024-03-17 00:00:00.000');

/*특정 날짜로 vote_id 가져오기*/
select vote_id
from vote
where VOTE_DATE = '2024-03-17 00:00:00.000';

/*attend테이블 조회*/
select * from attend;

/*미참여인원 조회*/
SELECT *
FROM FB_USER
WHERE 1=1
AND FB_USER_ID IN (
	SELECT FU.FB_USER_ID 
FROM FB_USER FU
LEFT JOIN (
	SELECT *
	FROM ATTEND
	WHERE VOTE_ID = (
		SELECT VOTE_ID
		FROM VOTE
		WHERE VOTE_DATE = '2024-03-17 00:00:00.000'
		)
) A
ON FU.FB_USER_ID = A.FB_USER_ID 
WHERE A.FB_USER_ID IS NULL
OR FU.FB_USER_ID IS NULL
	)
AND FB_USER_DEL_YN = 'N'
ORDER BY FB_USER_ID DESC;
