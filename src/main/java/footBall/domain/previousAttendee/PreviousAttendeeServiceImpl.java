package footBall.domain.previousAttendee;

import footBall.domain.attendee.AttendeeServiceImpl;
import footBall.domain.attendee.VoteDto;
import footBall.domain.teamBuilder.TeamDto;
import footBall.domain.user.UserResponse;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
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
        LocalDateTime sunday = new VoteDto().getSunday();
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

    // 투표id로 참석한 인원 가져오기
    @Override
    public List<UserResponse> getAttendee(Long voteId) {
        return sqlSession.selectList("PreviousAttendeeMapper.getAttendee", voteId);
    }

    // 날짜별 팀 멤버 조회
    @Override
    public List<UserResponse> getMemberByTeamName(String teamName, Long voteId) {
        HashMap<String, Object> teamMap = new HashMap<>();
        teamMap.put("teamName", teamName);
        teamMap.put("voteId", voteId);
        return sqlSession.selectList("PreviousAttendeeMapper.getMemberByTeamName", teamMap);
    }

    // 날짜별 팀 내용 조회
    @Override
    public List<TeamDto> getTeams(Long voteId) {
        return sqlSession.selectList("PreviousAttendeeMapper.getTeams", voteId);
    }
}
