package footBall.attendee;

import lombok.Data;

@Data
public class AttendDto {
    private int attend_id;      // 참석 id
    private int vote_id;        // 투표 id
    private int fb_user_id;     // 유저 id
    private int attend_status;  // 참석상태
}
