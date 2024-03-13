package footBall.fee;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeeServiceImpl implements FeeService{

    @Autowired
    SqlSession sqlSession;
    @Override
    public int feeInsert(FeeDto checkboxData) {
        return sqlSession.insert("FeeMapper.feeInsert",checkboxData);
    }
}
