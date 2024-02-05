package footBall.freeBoardComment;

import java.util.List;

public interface FbcService {
    List<FbcResponse> findList(int id);

    int insert(FbcRequest dto);
}
