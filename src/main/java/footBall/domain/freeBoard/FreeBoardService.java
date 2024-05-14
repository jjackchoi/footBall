package footBall.domain.freeBoard;

import footBall.common.co.Criteria;

import java.util.List;

public interface FreeBoardService {
    // 글 조회
//    List<FreeBoardResponse> findAll();

    //글 작성
    int boardCreate(FreeBoardRequest dto);

    //글 단건조회
    FreeBoardResponse findOne(int id);

    // 글 수정
    int boardUpdate(FreeBoardRequest dto);

    // 글 삭제
    int deleteOne(int id);

    // 내가 쓴 글 조회
    List<FreeBoardResponse> findByUserId(Integer userId);

    // 전체 글 카운트
    int allCount();

    List<FreeBoardResponse> findAll(Criteria cri);

    List<FreeBoardResponse> searchBoard(Criteria criteria);

    // 검색결과 카운트
    int allSeachCount(String cri);

    // 댓글 갯수 가져오기
    List<FreeBoardResponse> getCommentsCount(List<FreeBoardResponse> list);
}
