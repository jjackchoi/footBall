package footBall.attendee;

public interface AttendeeService {
    // 투표대상 날짜 생성
    int createDate(AttendeeDto params);

    // 투표대상 날짜 삭제
    int deleteDate(AttendeeDto params);

    // 투표대상 날짜 조회
    AttendeeDto getDate(AttendeeDto params);

    // 투표대상 날짜 존재여부 판별
    int findDate();
}
