package footBall.freeBoard;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
