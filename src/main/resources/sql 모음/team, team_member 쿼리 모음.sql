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

/*특정 날짜 특정 팀에 멤버 조회*/
SELECT *
FROM MEMBER
WHERE MEMBER_ID IN(
	SELECT MEMBER_ID
	FROM TEAM_MEMBER 
	WHERE 1=1
	AND TEAM_ID = (
		SELECT TEAM_ID
		FROM TEAM
		WHERE 1=1
		AND TEAM_NAME = 'C팀'
		AND VOTE_ID = (
			SELECT VOTE_ID 
			FROM VOTE
			WHERE VOTE_DATE = '2024-04-21 00:00:00.000'
		)
	)
);

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