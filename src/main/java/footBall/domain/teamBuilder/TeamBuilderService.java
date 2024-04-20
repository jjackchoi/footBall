package footBall.domain.teamBuilder;

import footBall.domain.member.MemberDto;

import java.util.List;

public interface TeamBuilderService {

    // 투표에서 참석한 인원 불러오기
    List<MemberDto> getAttendee();

    // 팀 섞기
    void shuffleTeams(Long numberOfTeams, List<MemberDto> attendees);

    // 팀 멤버 조회
    List<MemberDto> showTeam(String teamName);

    // 팀 내용 조회
    List<TeamDto> getTeams();
}
