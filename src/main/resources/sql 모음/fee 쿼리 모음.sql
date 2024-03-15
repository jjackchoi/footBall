select * from fee f ;

/*현재 최대 fee_id 몇인지 확인*/
select max(fee_id) from fee;

/*삭제 시 fee_id 1로 초기화*/
alter table fee auto_increment = 1;

/*전체 삭제*/
delete from fee;

