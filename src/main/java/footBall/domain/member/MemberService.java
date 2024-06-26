package footBall.domain.member;

import java.util.List;

public interface MemberService {

    // 멤버 권한 Y로 업데이트 및 멤버 데이터 생성
    void grantAuthority(int parsedUserId);

    // 멤버 권한 N로 업데이트 및 멤버 데이터 삭제
    void revokeAuthority(int parsedUserId);

    // 멤버 전체 조회
    List<MemberDto> getMembers();

    // 멤버 어빌리티 및 평균 티어 적용
    void applyAbility(MemberDto params);

    // userId로 멤버 조회
    MemberDto getMemberByUserId(MemberDto params);
}
