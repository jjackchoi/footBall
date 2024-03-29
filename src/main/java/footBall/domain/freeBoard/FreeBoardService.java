package footBall.domain.freeBoard;

import java.util.List;
import java.util.Map;

public interface FreeBoardService {
    // 글 조회
    List<FreeBoardResponse> findAll();

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

    // 검색한리스트
    List<FreeBoardResponse> searchFreeBoardPosts(String keyword);

    //전체글 카운트
    int allCount();

    //페이징에 맞는 리스트
    List<FreeBoardResponse> findPaginatedData(Map<String, Integer> params);
}
