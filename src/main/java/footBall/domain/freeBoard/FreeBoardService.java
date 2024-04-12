package footBall.domain.freeBoard;

import footBall.common.co.Criteria;

import java.util.List;
import java.util.Map;

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

    // 내가쓴글조회
    List<FreeBoardResponse> findByUserId(Integer userId);

    //전체글 카운트
    int allCount();

    List<FreeBoardResponse> findAll(Criteria cri);

    List<FreeBoardResponse> searchBoard(Criteria criteria);

    //검색결과 카운트
    int allSeachCount(String cri);
}
