package footBall.domain.freeBoard;

import footBall.common.co.Criteria;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class FreeBoardServiceImpl implements FreeBoardService{
    @Autowired
    SqlSession sqlSession;
    @Override
    public int boardCreate(FreeBoardRequest dto) {
        return sqlSession.insert("FreeBoardMapper.boardCreate",dto);
    }
    @Override
    public FreeBoardResponse findOne(int freeBoardId) {
        return sqlSession.selectOne("FreeBoardMapper.findOne",freeBoardId);
    }
    //글 수정
    @Override
    public int boardUpdate(FreeBoardRequest dto) {
        return sqlSession.update("FreeBoardMapper.boardUpdate",dto);
    }

    //글 삭제
    @Override
    public int deleteOne(int id) {
        return sqlSession.delete("FreeBoardMapper.deleteOne",id);
    }

    // 내가쓴글조회
    @Override
    public List<FreeBoardResponse> findByUserId(Integer userId) {
        return sqlSession.selectList("FreeBoardMapper.findByUserId",userId);
    }

    //데이터 전체개수
    @Override
    public int allCount() {
        return sqlSession.selectOne("FreeBoardMapper.allCount");

    }

    @Override
    public List<FreeBoardResponse> findAll(Criteria cri) {
        return sqlSession.selectList("FreeBoardMapper.findAll",cri);
    }

    @Override
    public List<FreeBoardResponse> searchBoard(Criteria criteria) {
        return sqlSession.selectList("FreeBoardMapper.searchBoard",criteria);
    }

    @Override
    public int allSeachCount(String cri) {
        return sqlSession.selectOne("FreeBoardMapper.allSearchCount",cri);
    }


}
