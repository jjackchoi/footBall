package footBall.domain.suggestionBoard;

import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SbcServiceImpl implements SbcService {

    private SqlSession sqlSession;

    @Override
    public int createSbc(SbcRequest params) {
        return sqlSession.insert("SbcMapper.createSbc", params);
    }
}
