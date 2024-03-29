package footBall.domain.fee;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeeServiceImpl implements FeeService{

    @Autowired
    SqlSession sqlSession;

    // 회비 납부 o
    @Override
    public int createFee(FeeDto checkboxData) {
        return sqlSession.insert("FeeMapper.createFee",checkboxData);
    }

    // 회비 납부 x
    @Override
    public int deleteFee(FeeDto checkboxData) {
        return sqlSession.delete("FeeMapper.deleteFee", checkboxData);
    }

    // 모든 회비정보 불러오기
    @Override
    public List<FeeDto> getAllUserFee() {
        return sqlSession.selectList("FeeMapper.getAllUserFee");
    }
}
