/*답변 생성*/
INSERT INTO SUGGESTION_BOARD_COMMENT (
	suggestion_board_id, sbc_author, sbc_text, sbc_reg_date, sbc_del_yn
)
VALUES(
	9, '짹초이', '안녕하시와요~', now(), 'N'
);

/*답변 생성 시 답변한 게시글 답변완료 상태로 변경*/
UPDATE suggestion_board 
SET suggestion_board_sol_yn = 'N'
WHERE suggestion_board_id = 9;

/*특정 답변 조회*/
SELECT sbc_author, sbc_text, sbc_reg_date 
FROM suggestion_board_comment
WHERE suggestion_board_id = 9;