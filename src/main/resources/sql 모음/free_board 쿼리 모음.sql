select * 
from free_board fb ;

show columns from free_board ;

/*게시글 만들기*/
insert into free_board (
	 FREE_BOARD_ID
	,FB_USER_ID
	,FREE_BOARD_AUTHOR 
	,FREE_BOARD_TITLE
	,FREE_BOARD_TEXT
	,FREE_BOARD_REG_DATE
	,FREE_BOARD_MOD_DATE
	,FREE_BOARD_IMG_PATH
	,FREE_BOARD_DEL_YN
)
values (0, 2, "우리현우", "오늘의 똥", "박규태!", now(), null, null, 0);

/*전체 게시글 조회*/
select free_board_id
        ,fb_user_id
        ,free_board_author
        ,free_board_title
        ,free_board_text
        ,free_board_reg_date
        ,free_board_mod_date
        ,free_board_img_path
        ,free_board_del_yn
from free_board
where 1=1
and FREE_BOARD_DEL_YN = 0
order by FREE_BOARD_ID desc;

select
            *
            from free_board fb 
        where 1=1
            and free_board_del_yn = 0
        order by
            free_board_id desc;