package footBall.domain.member;

import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final SqlSession sqlSession;

    // 멤버 권한 Y로 업데이트 및 멤버 데이터 생성
    @Override
    public void grantAuthority(int parsedUserId) {
        sqlSession.update("MemberMapper.grantUpdate", parsedUserId);
        sqlSession.insert("MemberMapper.createMember", parsedUserId);
    }

    // 멤버 권한 N으로 업데이트 및 멤버 데이터 삭제
    @Override
    public void revokeAuthority(int parsedUserId) {
        sqlSession.update("MemberMapper.grantDelete", parsedUserId);
        sqlSession.delete("MemberMapper.deleteMember", parsedUserId);
    }

    // 멤버 전체 조회
    @Override
    public List<MemberDto> getMembers() {
        return sqlSession.selectList("MemberMapper.getMembers");
    }

    // 멤버 어빌리티 및 평균 티어 적용
    @Override
    public void applyAbility(MemberDto params) {
        params.calculateAbilityAverage();
        sqlSession.update("MemberMapper.applyAbility", params);
    }

    // userId로 멤버 조회
    @Override
    public MemberDto getMemberByUserId(MemberDto params) {
        return sqlSession.selectOne("MemberMapper.getMemberByUserId", params);
    }




}
