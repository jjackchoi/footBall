/*데이터 조회 및 삭제*/
select * from fb_user fu ;
select * from free_board fb ;
select * from free_board_comment fbc ;
select * from suggestion_board sb ;
select * from attend a ;
select * from fee;

/*pk, fk 상관없이 해당데이터 삭제하고 싶을 때 0, 원복 1*/
set foreign_key_checks = 1;

/*이 순서대로 삭제*/
delete from free_board ;
delete from free_board_comment ;
delete from suggestion_board ;
delete from attend ;
delete from fee;
delete from fb_user ;

/*AUTO_INCREMENT 값을 초기화 후, 테이블 안의 모든 데이터의 ID값을 재조정 하기*/
ALTER TABLE fb_user AUTO_INCREMENT=1;
SET @COUNT = 0;
UPDATE fb_user SET fb_user_id = @COUNT:=@COUNT+1;