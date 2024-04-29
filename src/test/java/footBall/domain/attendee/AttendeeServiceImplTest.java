package footBall.domain.attendee;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class AttendeeServiceImplTest {

    @Autowired
    private AttendeeService attendeeService;

    // 미참여 인원 투표 기능 테스트
    @Test
    void createVote() {
        // 해당 for문의 i는 fb_user 테이블의 fb_user_id 칼럼 조건이 만족하는 것을 모아둔 것임
        for (int i = 11; i <= 12; i ++){
            AttendDto dto = new AttendDto();
            dto.setVoteId(27); // 날짜별 voteId 삽입
            dto.setAttendStatus("Y"); // 투표 상태별 코드 (Y = 참석, N = 불참, P = 미정)
            dto.setFbUserId(i); // 조건 1.fb_user테이블의 fb_user_member_yn이 Y인 경우 2. member테이블의 member_ability_avg가 존재하는 경우
            attendeeService.createVote(dto);
        }
    }
}