package footBall.domain.previousAttendee;

import footBall.domain.attendee.VoteDto;
import footBall.domain.user.UserResponse;

import java.util.List;

public interface PreviousAttendeeService {
    // 모든 투표 가져오기
    List<VoteDto> getAllVote();

    // 투표한 인원 조회
    List<UserResponse> getVotedUsers(Long voteId);

    // 투표id로 투표대상날짜 가져오기
    VoteDto getVoteDate(Long voteId);

}
