package footBall.domain.freeBoardComment;

import java.util.List;

public interface FbcService {
    //댓글 조회
    List<FbcResponse> findList(int id);

    //댓글 작성
    int insert(FbcRequest dto);

    //댓글 삭제
    void deleteList(int id);
}
