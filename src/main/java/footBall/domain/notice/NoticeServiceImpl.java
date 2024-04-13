package footBall.domain.notice;

import footBall.common.co.Criteria;
import groovy.util.logging.Slf4j;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService{

    private final SqlSession sqlSession;

    // 공지사항 전체 조회
    @Override
    public List<NoticeResponse> getNotices(Criteria cri) {
        return sqlSession.selectList("NoticeMapper.getNotices",cri);
    }

    // 공지사항 생성
    @Override
    public int createNotice(NoticeRequest params) {
        return sqlSession.insert("NoticeMapper.createNotice", params);
    }

    @Override
    public int allCount() {
        return sqlSession.selectOne("NoticeMapper.allCount");
    }

    @Override
    public int searchAllCount(String keyword) {
        return sqlSession.selectOne("NoticeMapper.searchAllCount",keyword);
    }

    @Override
    public List<NoticeResponse> getSearchNotices(Criteria cri) {
        return sqlSession.selectList("NoticeMapper.searchAllList",cri);
    }
}
