package footBall.domain.teamBuilder;

import footBall.domain.member.MemberDto;

import java.util.List;

public interface TeamBuilderService {
    // 투표에서 참석한 인원 불러오기
    List<MemberDto> getAttendee();
}
