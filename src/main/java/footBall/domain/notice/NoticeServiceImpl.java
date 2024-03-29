package footBall.domain.notice;

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
    public List<NoticeResponse> getNotices() {
        return sqlSession.selectList("NoticeMapper.getNotices");
    }

    // 공지사항 생성
    @Override
    public int createNotice(NoticeRequest params) {
        return sqlSession.insert("NoticeMapper.createNotice", params);
    }
}
