package footBall.domain.freeBoardComment;

import java.util.List;

public interface FbcService {
    // 댓글 조회
    List<FbcResponse> findCommentsById(int id);

    // 댓글 작성
    int createComment(FbcRequest dto);

    // 댓글 삭제
    void deleteComments(int id);
}
