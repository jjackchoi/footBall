package footBall.domain.teamBuilder;

import footBall.domain.attendee.VoteDto;
import footBall.domain.member.MemberDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class TeamBuilderServiceImpl implements TeamBuilderService {

    private final SqlSession sqlSession;

    // 투표에서 참석한 인원 불러오기
    @Override
    public List<MemberDto> getAttendee(){
        LocalDateTime sunday = new VoteDto().getSunday();
        return sqlSession.selectList("TeamBuilderMapper.getAttendee", sunday);
    }
}
