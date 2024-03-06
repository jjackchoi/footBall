package footBall.attendee;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AttendeeServiceImpl implements AttendeeService{
    @Autowired
    SqlSession sqlSession;

    // 투표대상 날짜 생성
    @Override
    public int createDate(AttendeeDto params) {
        return sqlSession.insert("AttendeeMapper.createDate", params);
    }

    @Override
    public int deleteDate(AttendeeDto params) {
        return sqlSession.delete("AttendeeMapper.deleteDate", params);
    }
}
