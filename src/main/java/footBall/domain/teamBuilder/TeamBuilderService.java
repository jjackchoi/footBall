package footBall.domain.teamBuilder;

import footBall.domain.user.UserResponse;

import java.util.List;

public interface TeamBuilderService {

    // 투표에서 참석한 인원 불러오기
    List<UserResponse> getAttendee();

    // 팀 섞기
    void shuffleTeams(Long numberOfTeams, List<UserResponse> attendees);

    // 팀 멤버 조회
    List<UserResponse> showTeam(String teamName);

    // 팀 내용 조회
    List<TeamDto> getTeams();
}
