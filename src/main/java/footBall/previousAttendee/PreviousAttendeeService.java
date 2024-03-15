package footBall.previousAttendee;

import footBall.attendee.VoteDto;

import java.util.List;

public interface PreviousAttendeeService {
    // 모든 투표 가져오기
    List<VoteDto> getAllVote();
}
