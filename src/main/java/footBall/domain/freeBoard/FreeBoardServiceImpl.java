package footBall.domain.freeBoard;

import footBall.common.co.Criteria;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
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

    // 댓글 갯수 가져오기
    @Override
    public List<FreeBoardResponse> getCommentsCount(List<FreeBoardResponse> list) {
        // 자유게시판의 id로 각 게시판의 댓글 수 세팅
        for (int i = 0; i < list.size(); i++){
            List<FreeBoardResponse> comments = sqlSession.selectList("FbcMapper.findList", list.get(i).getFreeBoardId());
            list.get(i).setFbcCommentsCount(comments.size()); // 댓글 수 세팅
        }
        return list;
    }


}
