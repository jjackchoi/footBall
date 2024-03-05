package footBall.attendee;

import java.time.LocalDateTime;

public interface AttendeeService {
    // 투표대상 날짜 생성
    int createDate(AttendeeDto voteDate);
}
