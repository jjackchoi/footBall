package footBall.freeBoardComment;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FbcServiceImpl implements FbcService {
    @Autowired
    private SqlSession sqlSession;
    
    // 댓글 작성
    @Override
    public int insert(FbcRequest dto) {
        return sqlSession.insert("FbcMapper.fbcCreate",dto);
    }

    // 댓글 조회
    @Override
    public List<FbcResponse> findList(int id) {
        return sqlSession.selectList("FbcMapper.findList",id);
    }
}
