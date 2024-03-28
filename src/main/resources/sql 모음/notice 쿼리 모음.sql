/*공지사항 테이블 칼럼 조회*/
show columns from notice_board;
/*공지사항 전체 조회*/
select * from notice;
/*공지사항 삭제 안된 것 조회*/
SELECT *
FROM notice
WHERE notice_del_yn = 'N'
ORDER BY notice_id DESC;

/*공지사항 생성*/
INSERT INTO notice(
	 notice_id
	,fb_user_id
	,notice_author
	,notice_title
	,notice_text
	,notice_reg_date
	,notice_mod_date
	,notice_img_path
	,notice_del_yn
)VALUES (
	 #{noticeid}
	,#{fb_user_id}
	,#{noticeAuthor}
	,#{noticeTitle}
	,#{noticeText}
	,now()
	,null
	,null
	,'N'
);