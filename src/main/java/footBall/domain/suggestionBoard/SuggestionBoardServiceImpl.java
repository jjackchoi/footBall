package footBall.domain.suggestionBoard;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SuggestionBoardServiceImpl implements SuggestionBoardService{
    @Autowired
    private SqlSession sqlSession;

    // 전체 조회
    @Override
    public List<SuggestionBoardResponse> findAll(){
        return sqlSession.selectList("SuggestionBoardMapper.findAll");
    }

    // 비밀글제외 조회
    @Override
    public List<SuggestionBoardResponse> findExcludeSecret() {
        return sqlSession.selectList("SuggestionBoardMapper.findExcludeSecret");
    }

    // 게시글 생성
    @Override
    public int create(SuggestionBoardRequest dto) {
        return sqlSession.insert("SuggestionBoardMapper.create", dto);
    }

    // 게시글 상세 조회
    @Override
    public SuggestionBoardResponse findById(int id) {
        return sqlSession.selectOne("SuggestionBoardMapper.findById", id);
    }

    // 내가쓴글조회
    @Override
    public List<SuggestionBoardResponse> findByUserId(int id) {
        return sqlSession.selectList("SuggestionBoardMapper.findByUserId", id);
    }
}
