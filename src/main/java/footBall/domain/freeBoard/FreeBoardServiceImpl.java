package footBall.domain.freeBoard;

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
    public List<FreeBoardResponse> findAll() {
        return sqlSession.selectList("FreeBoardMapper.findAll");
    }
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

    // 검색한리스트
    @Override
    public List<FreeBoardResponse> searchFreeBoardPosts(String freeBoardTitle) {
        return sqlSession.selectList("FreeBoardMapper.findKeyword",freeBoardTitle);
    }
    //데이터 전체개수
    @Override
    public int allCount() {
        return sqlSession.selectOne("FreeBoardMapper.allCount");

    }

    //페이징 후 리스트
    @Override
    public List<FreeBoardResponse> findPaginatedData(Map<String, Integer> params) {
        return sqlSession.selectList("FreeBoardMapper.findPaginatedData",params);
    }


}
