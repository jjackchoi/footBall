package footBall.attendee;

import footBall.user.UserResponse;

import java.util.List;

public interface AttendeeService {

    // 미참여인원 가져오기
    List<UserResponse> getNonattendanceUser();

    // 투표대상 날짜 생성
    int createDate(VoteDto params);

    // 투표대상 날짜 삭제
    int deleteDate(VoteDto params);

    // 투표대상 날짜 조회
    VoteDto getDate(VoteDto params);

    // 투표대상 날짜 존재여부 판별
    int findDate();

    // 참석인원 조회
    List<UserResponse> getAttendedUser();

    // 불참인원 조회

    // 미정인원 조회
}
