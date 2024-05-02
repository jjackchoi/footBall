/*팀 조회*/
SELECT * FROM TEAM;

/*참석인원 조회*/
SELECT FU.FB_USER_ID, FU.FB_USER_NAME, m.MEMBER_ABILITY_AVG
FROM FB_USER FU
LEFT OUTER JOIN MEMBER M
ON FU.FB_USER_ID = M.FB_USER_ID
WHERE 1=1
AND M.MEMBER_ABILITY_AVG IS NOT NULL
AND FU.FB_USER_ID IN(
    SELECT FB_USER_ID
    FROM ATTEND
    WHERE 1=1
    AND VOTE_ID = (
        SELECT VOTE_ID
        FROM VOTE
        WHERE VOTE_DATE = '2024-05-05 00:00:00.000'
    )
    AND ATTEND_STATUS = 'Y'
)
ORDER BY FU.FB_USER_BIRTH;

/*팀 생성*/
INSERT INTO TEAM(VOTE_ID, TEAM_NAME)
VALUES(
	(
		SELECT VOTE_ID 
		FROM VOTE
		WHERE VOTE_DATE = '2024-04-21 00:00:00.000'
	), 'A팀' 
);

/*팀 삭제*/
DELETE FROM TEAM
WHERE VOTE_ID = (
	SELECT VOTE_ID 
	FROM VOTE
	WHERE VOTE_DATE = '2024-04-21 00:00:00.000'
);

/*팀 멤버 전체 조회*/
SELECT * FROM TEAM_MEMBER;

/*날짜, 팀명으로 팀원 조회*/
SELECT fu.fb_user_id, fu.fb_user_name, m.member_ability_avg
FROM FB_USER fu
LEFT OUTER JOIN MEMBER m
ON fu.fb_user_id = m.fb_user_id
WHERE fu.fb_user_id IN(
	SELECT fb_user_id 
	FROM TEAM_MEMBER
	WHERE team_id = (
		SELECT team_id
		FROM TEAM
		WHERE 1=1
		AND team_name = 'C팀'
		AND vote_id = (
			SELECT vote_id 
			FROM VOTE
			WHERE vote_date = '2024-05-05 00:00:00.000'
		)
	)
)
ORDER BY fu.fb_user_birth;

/*팀 멤버 생성*/
INSERT INTO TEAM_MEMBER(TEAM_ID, MEMBER_ID)
VALUES(
	(
		select team_id
		from team
		where 1=1 
		and vote_id = (
			select VOTE_ID 
			from vote
			where vote_date = '2024-04-21 00:00:00.000'
		)
		and TEAM_NAME = 'A팀'
	), 5
);

/*팀 멤버 삭제*/
DELETE FROM TEAM_MEMBER
WHERE TEAM_ID IN (
	SELECT TEAM_ID
	FROM TEAM
	WHERE VOTE_ID = (
		SELECT VOTE_ID 
		FROM VOTE
		WHERE VOTE_DATE = '2024-04-21 00:00:00.000'
	)
);

/*팀 이름을 조회해서 배열에 담음, 배열 갯수로 라디오버튼 체크 설정, div보이게 하고 팀원 렌더링*/
SELECT *
FROM TEAM
WHERE VOTE_ID = (
	SELECT VOTE_ID 
	FROM VOTE
	WHERE VOTE_DATE = '2024-04-21 00:00:00.000'
);