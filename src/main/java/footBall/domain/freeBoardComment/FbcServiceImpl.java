package footBall.domain.freeBoardComment;

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
    public int createComment(FbcRequest dto) {
        return sqlSession.insert("FbcMapper.createComment",dto);
    }

    // 댓글 삭제
    @Override
    public void deleteComments(int id) {
        sqlSession.delete("FbcMapper.deleteComments",id);
    }

    // 댓글 조회
    @Override
    public List<FbcResponse> findCommentsById(int id) {
        return sqlSession.selectList("FbcMapper.findList",id);
    }
}
