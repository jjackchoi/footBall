ALTER TABLE football.fb_user MODIFY COLUMN FB_USER_PHONE varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL NULL COMMENT '유저 휴대폰번호';
/*유저권한 칼럼 추가*/
ALTER TABLE football.fb_user ADD FB_USER_IS_ADMIN varchar(2) DEFAULT 'N' NOT NULL COMMENT '유저 권한';
