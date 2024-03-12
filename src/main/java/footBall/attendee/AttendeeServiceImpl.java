package footBall.attendee;

import footBall.user.UserResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class AttendeeServiceImpl implements AttendeeService{
    @Autowired
    SqlSession sqlSession;

    // 미참여 인원 가져오기
    @Override
    public List<UserResponse> getNonattendanceUser() {
        // 일요일 가져오기
        LocalDateTime sunday = this.getSunday();
        return sqlSession.selectList("AttendeeMapper.getNonattendanceUser", sunday);
    }

    // 투표한 인원 조회
    @Override
    public List<UserResponse> votedUser() {
        // 일요일 가져오기
        LocalDateTime sunday = this.getSunday();
        return sqlSession.selectList("AttendeeMapper.getAttendedUser", sunday);
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

    // 투표 대상 날짜 조회
    @Override
    public VoteDto getDate(VoteDto params) {
        return sqlSession.selectOne("AttendeeMapper.getDate", params);
    }

    // 투표 대상 날짜 존재 여부 판별
    @Override
    public int findDate() {
        // 일요일 가져오기
        LocalDateTime sunday = this.getSunday();

        // dto에 넣기
        VoteDto dto = new VoteDto();
        dto.setVoteDate(sunday);

        return sqlSession.selectOne("AttendeeMapper.findDate", dto);
    }

    // 일요일 가져오기
    public LocalDateTime getSunday(){
        // 현재 날짜 가져오기
        LocalDateTime today = LocalDateTime.now();

        // 날짜의 요일 가져오기
        DayOfWeek todayOfWeek = today.getDayOfWeek();
        log.info(String.valueOf(todayOfWeek));

        // 요일 숫자 출력(월요일:1, 화요일:2,..., 일요일:7)
        int todayOfWeekValue = todayOfWeek.getValue();
        log.info("오늘 날짜의 요일 숫자 " + todayOfWeekValue);

        // 투표 대상 일요일 날짜 가져오기
        LocalDateTime sunday = null;
        if (todayOfWeekValue == 7){
            sunday = today;
        } else {
            sunday = LocalDateTime.now();
            sunday = sunday.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY)); // 다음 일요일 가져오기
        }
        sunday = sunday.withHour(0).withMinute(0).withSecond(0).withNano(0);
        log.info("돌아오는주 일요일의 정보는 : " + sunday);
        return sunday;
    }
}
