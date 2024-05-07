package footBall.domain.attendee;

import footBall.domain.user.UserResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class AttendeeServiceImpl implements AttendeeService{

    private final SqlSession sqlSession;

    // 미참여 인원 가져오기
    @Override
    public List<UserResponse> findNonattendanceUser() {
        // 일요일 가져오기
        LocalDateTime sunday = new VoteDto().getSunday();
        return sqlSession.selectList("AttendeeMapper.findNonattendanceUser", sunday);
    }

    // 투표한 인원 조회
    @Override
    public List<UserResponse> findVotedUser() {
        // 일요일 가져오기
        LocalDateTime sunday = new VoteDto().getSunday();
        return sqlSession.selectList("AttendeeMapper.findVotedUser", sunday);
    }

    // 투표 대상 날짜 생성
    @Override
    public int createDate(VoteDto params) {
        return sqlSession.insert("AttendeeMapper.createDate", params);
    }

    // 투표 대상 날짜 삭제
    @Override
    public int deleteDate(VoteDto params) {
        return sqlSession.delete("AttendeeMapper.deleteDate", params);
    }

    // 투표 대상 날짜 데이터의 id에 종속되어있는 참석 데이터 삭제
    @Override
    public int deleteAttend(VoteDto params) {
        return sqlSession.delete("AttendeeMapper.deleteAttend", params);
    }

    // 투표 대상 날짜 조회
    @Override
    public VoteDto getDate(VoteDto params) {
        return sqlSession.selectOne("AttendeeMapper.getDate", params);
    }

    // 투표 대상 날짜 존재 여부 판별
    @Override
    public int findDate() {
        // 일요일 가져오기
        LocalDateTime sunday = new VoteDto().getSunday();

        // dto에 넣기
        VoteDto dto = new VoteDto();
        dto.setVoteDate(sunday);

        return sqlSession.selectOne("AttendeeMapper.findDate", dto);
    }

    // 투표 값 업데이트
    @Override
    public int updateVote(AttendDto params) {
        Map<String, Object> objectMap = mappingParams(params);
        return sqlSession.update("AttendeeMapper.updateVote", objectMap);
    }

    // 미참여인원 투표
    @Override
    public int createVote(AttendDto params) {
        Map<String, Object> objectMap = mappingParams(params);
        return sqlSession.insert("AttendeeMapper.createVote", objectMap);
    }

    // 투표한 해당 유저의 참석여부 값 가져오기
    @Override
    public String getAttendStatus(Long fbUserId) {
        Map<String, Object> objectMap = new HashMap<>();
        objectMap.put("fbUserId", fbUserId);
        objectMap.put("sunday", new VoteDto().getSunday());
        return sqlSession.selectOne("AttendeeMapper.getAttendStatus",objectMap);
    }

    // 로그인된 유저의 멤버여부 가져오기
    @Override
    public String getMemberYn(Long fbUserId) {
        return sqlSession.selectOne("AttendeeMapper.getMemberYn", fbUserId);
    }

    // 로그인된 유저의 어빌리티 가져오기
    @Override
    public Double getAbilityAvg(Long fbUserId) {
        return sqlSession.selectOne("AttendeeMapper.getAbilityAvg", fbUserId);
    }

    // 가져온 파라미터값들과 투표대상날짜 매핑
    public Map<String, Object> mappingParams(AttendDto params){
        LocalDateTime sunday = new VoteDto().getSunday();
        Map<String, Object> objectMap = new HashMap<>();
        objectMap.put("attendStatus", params.getAttendStatus());
        objectMap.put("fbUserId", params.getFbUserId());
        objectMap.put("sunday", sunday);
        return objectMap;
    }
}
