/*member auto_increment 1로 초기화*/
ALTER TABLE member AUTO_INCREMENT = 1;

/*member의 모든 데이터를 delete하고, auto_increment를 1로 초기화*/
truncate member;

/*member ability_avg 칼럼 생성*/
ALTER TABLE member
ADD COLUMN MEMBER_ABILITY_AVG DOUBLE NULL COMMENT '멤버 능력치 평균';

/*member 칼럼 보기*/
SHOW COLUMNS FROM MEMBER;
