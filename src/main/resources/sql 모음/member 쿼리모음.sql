/*유저 조회*/
select * from fb_user where FB_USER_DEL_YN = 'N';

/*멤버 조회*/
select * from member;

/*멤버 데이터 생성*/
INSERT INTO member(
	member_id, fb_user_id, member_name
)
VALUES(
	0, 
	1, 
	(SELECT fb_user_name 
	 FROM fb_user
	 WHERE fb_user_id = 1)
);

/*멤버 데이터 삭제*/
DELETE FROM member 
WHERE fb_user_id = 17; 

alter table member auto_increment = 1;

/*멤버 어빌리티 및 평균 티어 적용*/
UPDATE member
SET member_dribble_ability = 1,
	member_passing_ability = 2,
	member_defending_ability = 3,
	member_stamina = 1,
	member_finishing_ability = 1,
	member_ability_avg = null
WHERE fb_user_id = 9;

/*userId로 멤버 조회*/
SELECT * 
FROM member 
WHERE fb_user_id = 9;

/*참석으로 투표한 인원 불러오기*/
SELECT *  
FROM member
WHERE fb_user_id IN(
	SELECT fb_user_id  
	FROM attend
	WHERE 1=1 
	AND vote_id = (
		SELECT vote_id
		FROM vote
		WHERE vote_date = '2024-04-14 00:00:00.000'
	)
	AND attend_status = 'Y'
);