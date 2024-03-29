select * from vote;
select * from fb_user;
select * from attend;

/*test data insert*/
insert into attend(VOTE_ID, FB_USER_ID , ATTEND_STATUS) values(19, 7, 'P');
insert into attend(VOTE_ID, FB_USER_ID , ATTEND_STATUS) values(19, 6, 'N');
insert into attend(VOTE_ID, FB_USER_ID , ATTEND_STATUS) values(19, 5, 'N');
insert into attend(VOTE_ID, FB_USER_ID , ATTEND_STATUS) values(19, 4, 'Y');
insert into attend(VOTE_ID, FB_USER_ID , ATTEND_STATUS) values(19, 3, 'Y');

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
			WHERE VOTE_DATE = '2024-03-31 00:00:00.000'
		)
	) A
	ON FU.FB_USER_ID = A.FB_USER_ID 
	WHERE A.FB_USER_ID IS NULL
	OR FU.FB_USER_ID IS NULL
)
AND FB_USER_DEL_YN = 'N'
ORDER BY FB_USER_ID DESC;

/*날짜에 따른 유저아이디(fb_user_id) 조회*/
select FB_USER_ID 
from attend
where 1=1
and attend_status = 'Y'
and vote_id = (
	select vote_id
	from vote
	where VOTE_DATE = '2024-03-17 00:00:00.000'
);

/*참석한 회원 조회*/
SELECT fu.fb_user_id
      ,fu.fb_user_email
      ,fu.fb_user_pswd
      ,fu.fb_user_nickname
      ,fu.fb_user_name
      ,fu.fb_user_birth
      ,fu.fb_user_phone
      ,fu.fb_user_address
      ,fu.fb_user_auth
      ,fu.fb_user_del_yn
      ,fu.fb_user_reg_date
      ,fu.fb_user_mod_date
      ,a.attend_status
FROM fb_user fu
LEFT JOIN attend a 
ON fu.fb_user_id = a.fb_user_id 
WHERE 1=1
AND fu.fb_user_id IN(
	SELECT fb_user_id 
	FROM attend
	WHERE vote_id = (
		SELECT vote_id
		FROM vote
		WHERE vote_date = '2024-03-17 00:00:00.000'
	)
)
AND fu.fb_user_del_yn = 'N'
ORDER BY fu.fb_user_id DESC;

/*미참여(미투표) 시 투표영역 버튼을 눌렀을 때*/
INSERT INTO attend(
	vote_id, fb_user_id , attend_status
) 
VALUES(
	(
		SELECT vote_id
	 	FROM vote
	 	WHERE vote_date = '2024-03-17 00:00:00.000'
 	), 26, 'P'
);

select * from attend
where FB_USER_ID  = 25;

/*투표영역 버튼에서 다른버튼을 클릭했을 시*/
UPDATE attend 
SET attend_status = "Y"
WHERE 1=1
AND fb_user_id = 26
AND vote_id = (
	SELECT vote_id
	FROM vote
 	WHERE vote_date = '2024-03-17 00:00:00.000'
);

/*미참여에 있는 사람이 투표영역 버튼을 눌렀을 때*/
INSERT INTO attend(
	vote_id, fb_user_id , attend_status 
)VALUES (
	(
		SELECT vote_id
		FROM vote
	 	WHERE vote_date = '2024-03-31 00:00:00.000'
 	)
 	, 16, 'N'
);

/*화면 로드 시 사용자의 참석여부에 따른 라디오버튼 고정*/
SELECT attend_status 
FROM attend
WHERE 1=1
AND fb_user_id = 26
AND vote_id = (
	SELECT vote_id
	FROM vote
	WHERE vote_date = "2024-03-17 00:00:00.000"
);

/*투표 대상 날짜 삭제*/
DELETE FROM vote
WHERE vote_date = "2024-03-17 00:00:00.000";

/*투표 대상 날짜 데이터의 id에 종속되어있는 참석 데이터 삭제*/
DELETE FROM attend 
WHERE
vote_id = (
	SELECT vote_id 
	FROM vote 
	WHERE vote_date = "2024-03-17 00:00:00.000"
);

/*vote_id로 vote_date 가져오기*/
SELECT vote_date
FROM vote
WHERE vote_id = 9;