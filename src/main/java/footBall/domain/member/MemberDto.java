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
    private Double memberAbilityAvg;

    // 멤버 능력치의 평균을 계산
    public void calculateAbilityAverage() {
        // 총합 계산
        Long totalAbility = memberDribbleAbility + memberPassingAbility + memberDefendingAbility +
                            memberStamina + memberFinishingAbility;
        // 평균 계산
        memberAbilityAvg = totalAbility/5.0;
    }
}
