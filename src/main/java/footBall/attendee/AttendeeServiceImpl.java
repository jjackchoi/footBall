package footBall.attendee;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AttendeeServiceImpl implements AttendeeService{
    @Autowired
    SqlSession sqlSession;

    @Override
    public int createDate(AttendeeDto voteDate) {
        return sqlSession.insert("AttendeeMapper.createDate", voteDate);
    }
}
