package footBall.freeBoardComment;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FbcServiceImpl {
    @Autowired
    private SqlSession sqlSession;
}
