package footBall.domain.suggestionBoard;

import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SbcServiceImpl implements SbcService {

    private final SqlSession sqlSession;

    // 답변 등록 및 해당 게시글 답변 완료 상태로 변경
    @Override
    @Transactional
    public void createSbc(SbcRequest params) {
        // 게시글 답변 완료 상태로 변경
        sqlSession.update("SbcMapper.updatePostSolYn", params);
        // 답변 등록
        sqlSession.insert("SbcMapper.createSbc", params);
    }

    // 해당 게시글 답변 조회
    @Override
    public SbcResponse getComment(int id) {
        return sqlSession.selectOne("SbcMapper.getComment", id);
    }
}
