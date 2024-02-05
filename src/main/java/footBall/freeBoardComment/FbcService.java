package footBall.freeBoardComment;

import java.util.List;

public interface FbcService {
    //댓글 조회
    List<FbcResponse> findList(int id);

    //댓글 작성
    int insert(FbcRequest dto);
}
