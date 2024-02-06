package footBall.freeBoard;

import java.util.List;

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
}
