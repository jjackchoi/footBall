package footBall.previousAttendee;

import footBall.attendee.AttendeeServiceImpl;
import footBall.attendee.VoteDto;
import footBall.user.UserResponse;
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

    // 투표한 인원 조회
    @Override
    public List<UserResponse> getVotedUsers(Long voteId) {
        return sqlSession.selectList("PreviousAttendeeMapper.getVotedUser", voteId);
    }

    // 투표id로 투표대상날짜 가져오기
    @Override
    public VoteDto getVoteDate(Long voteId) {
        return sqlSession.selectOne("PreviousAttendeeMapper.getVoteDate", voteId);
    }


}
