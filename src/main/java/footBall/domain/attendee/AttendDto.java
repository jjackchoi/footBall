package footBall.domain.attendee;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AttendDto {
    private int attendId;      // 참석 id
    private int voteId;        // 투표 id
    private int fbUserId;     // 유저 id
    private String attendStatus;  // 참석상태
    private LocalDateTime attendModDate; // 참석 수정일
}
