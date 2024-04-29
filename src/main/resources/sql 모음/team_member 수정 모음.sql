/*member_id 참조 해제*/
ALTER TABLE football.team_member DROP FOREIGN KEY team_member_ibfk_2;
ALTER TABLE football.team_member DROP COLUMN MEMBER_ID;
/*fb_user_id 참조*/
ALTER TABLE football.team_member ADD FB_USER_ID INT(11) NOT NULL COMMENT '유저 ID';
ALTER TABLE football.team_member ADD CONSTRAINT team_member_fb_user_FK FOREIGN KEY (FB_USER_ID) REFERENCES football.fb_user(FB_USER_ID);