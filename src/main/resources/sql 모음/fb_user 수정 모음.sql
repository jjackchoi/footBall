ALTER TABLE football.fb_user MODIFY COLUMN FB_USER_PHONE varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL NULL COMMENT '유저 휴대폰번호';
/*멤버여부 칼럼 추가*/
ALTER TABLE football.fb_user ADD FB_USER_MEMBER_YN varchar(2) NOT NULL COMMENT '유저 멤버여부';
/*특기, 주포지션, 이미지 칼럼 추가*/
ALTER TABLE football.fb_user ADD FB_USER_SPECIALTY varchar(20) NULL COMMENT '유저 특기';
ALTER TABLE football.fb_user ADD FB_USER_MAIN_POSITION varchar(20) NULL COMMENT '유저 주포지션';
ALTER TABLE football.fb_user ADD FB_USER_IMG varchar(20) NULL;
/*이미지 크기 20->100*/
ALTER TABLE football.fb_user MODIFY COLUMN FB_USER_IMG varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL NULL;


