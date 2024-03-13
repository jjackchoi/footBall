package footBall.attendee;

import footBall.user.UserResponse;

import java.util.List;

public interface AttendeeService {

    // 미참여 인원 가져오기
    List<UserResponse> findNonattendanceUser();

    // 투표 대상 날짜 생성
    int createDate(VoteDto params);

    // 투표 대상 날짜 삭제
    int deleteDate(VoteDto params);

    // 투표 대상 날짜 조회
    VoteDto getDate(VoteDto params);

    // 투표 대상 날짜 존재 여부 판별
    int findDate();

    // 투표한 인원 조회
    List<UserResponse> findVotedUser();

    // 투표 값 업데이트
    int updateVote(AttendDto params);

    // 미참여인원 투표
    int createVote(AttendDto params);
}
