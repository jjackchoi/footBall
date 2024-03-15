package footBall.previousAttendee;

import footBall.attendee.AttendeeServiceImpl;
import footBall.attendee.VoteDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PreviousAttendeeServiceImpl implements PreviousAttendeeService {
    @Autowired
    SqlSession sqlSession;

    @Autowired
    AttendeeServiceImpl attendeeService;

    // 모든 투표 가져오기
    @Override
    public List<VoteDto> getAllVote() {
        LocalDateTime sunday = attendeeService.getSunday();
        return sqlSession.selectList("PreviousAttendeeMapper.getAllVote", sunday);
    }


}
