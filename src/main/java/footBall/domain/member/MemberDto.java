package footBall.domain.member;

import lombok.Data;

@Data
public class MemberDto {
    private Long memberId;
    private Long fbUserId;
    private String memberName;
    private Long memberDribbleAbility;
    private Long memberPassingAbility;
    private Long memberDefendingAbility;
    private Long memberStamina;
    private Long memberFinishingAbility;
}
