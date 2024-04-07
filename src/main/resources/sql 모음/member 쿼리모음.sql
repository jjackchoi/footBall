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