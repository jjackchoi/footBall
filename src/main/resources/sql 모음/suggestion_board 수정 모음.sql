/*suggestion_board 수정 모음*/
ALTER TABLE football.suggestion_board ADD SUGGESTION_BOARD_RESOLUTION varchar(2) NOT NULL COMMENT '건의게시판 해결여부';
ALTER TABLE football.suggestion_board CHANGE SUGGESTION_BOARD_RESOLUTION SUGGESTION_BOARD_SOL_YN varchar(2) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '건의게시판 해결여부';
select *
from suggestion_board
where suggestion_board.SUGGESTION_BOARD_DEL_YN = 'N';
/*SUGGESTION_BOARD_SEC_YN 칼럼 추가*/
ALTER TABLE football.suggestion_board ADD SUGGESTION_BOARD_SEC_YN varchar(2) NOT NULL;
ALTER TABLE football.suggestion_board MODIFY COLUMN SUGGESTION_BOARD_SEC_YN varchar(2) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT "N" NOT NULL;



