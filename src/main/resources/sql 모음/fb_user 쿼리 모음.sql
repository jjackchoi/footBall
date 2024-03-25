show columns from fb_user;
select *
from fb_user fu ;

insert into fb_user(
	 FB_USER_ID
	,FB_USER_EMAIL
	,FB_USER_PSWD
	,FB_USER_NICKNAME
	,FB_USER_NAME
	,FB_USER_BIRTH
	,FB_USER_PHONE
	,FB_USER_ADDRESS
	,FB_USER_AUTH
	,FB_USER_DEL_YN
	,FB_USER_REG_DATE
	,FB_USER_MOD_DATE)
values(
	0
	,"chltmdals@naver.com"
	,"1234"
	,"짹초이"
	,"최승민"
	,"19941021"
	,"01079249553"
	,"경기도 광명시 광명로848번길"
	,0
	,0
	,now()
	,null
);

select * from fb_user fu ;

select *
from fb_user
where 1=1
and FB_USER_EMAIL = 'parkhyenwoo@naver.com'
and FB_USER_DEL_YN = 0;

select
   *
from
    fb_user
where 1=1
and fb_user_email = 'parkhyenwoo@naver.com'
and fb_user_del_yn = 0;

/*업데이트*/
UPDATE fb_user
SET fb_user_pswd = #{fbUserPswd}
WHERE fb_user_name = #{fbUserName} AND fb_user_email = #{fbUserEmail};   

/*유저 멤버만 조회*/
SELECT *
FROM fb_user
WHERE 1=1
AND fb_user_member_yn = 'Y'
AND fb_user_del_yn = 'N'
ORDER BY fb_user_birth ASC;