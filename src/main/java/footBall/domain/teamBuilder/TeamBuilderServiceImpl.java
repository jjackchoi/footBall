package footBall.domain.teamBuilder;

import footBall.domain.attendee.VoteDto;
import footBall.domain.member.MemberDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class TeamBuilderServiceImpl implements TeamBuilderService {

    private final SqlSession sqlSession;

    // 투표에서 참석한 인원 불러오기
    @Override
    public List<MemberDto> getAttendee(){
        LocalDateTime sunday = new VoteDto().getSunday();
        return sqlSession.selectList("TeamBuilderMapper.getAttendee", sunday);
    }

    // 팀 섞기
    @Override
    @Transactional
    public void shuffleTeams(Long numberOfTeams, List<MemberDto> attendees) {
        Boolean allEqual;
        List<Double> averages;

        do {
            // 참석자 셔플
            Collections.shuffle(attendees);

            // 팀을 나눔
            List<List<MemberDto>> teams = new ArrayList<>();
            for (int i = 0; i < numberOfTeams; i++) {
                teams.add(new ArrayList<>());
            }

            // 나눈 팀에 셔플한 멤버들 적재
            for (int i = 0; i < attendees.size(); i++) {
                teams.get((int)(i % numberOfTeams)).add(attendees.get(i));
            }

            // 팀의 능력치 평균 계산
            averages = new ArrayList<>();
            for (List<MemberDto> team : teams) {
                Double average = team.stream().mapToDouble(MemberDto::getMemberAbilityAvg).average().orElse(0);
                BigDecimal bd = new BigDecimal(average);
                bd = bd.setScale(1, RoundingMode.HALF_UP);
                Double roundedAverage = bd.doubleValue();
                averages.add(roundedAverage);
            }

            // 모든 평균이 같은지 확인(HashSet은 집합을 구현한 자료구조로 중복을 허용하지 않음)
            // 즉, 값이 전부 같으면 중복이 제거되어 한개만 남음으로 size()는 1이 됨
            allEqual = new HashSet<>(averages).size() == 1;

            // 평균이 같으면 팀, 팀 멤버 생성
            if(allEqual){
                LocalDateTime sunday = new VoteDto().getSunday();
                String[] teamName = {"A팀", "B팀", "C팀", "D팀"};

                // 팀, 팀 멤버 생성 전 초기화
                sqlSession.delete("TeamBuilderMapper.deleteTeamMember", sunday);
                sqlSession.delete("TeamBuilderMapper.deleteTeam", sunday);

                // 팀 생성 및 팀 멤버 생성
                for (int i = 0; i < teams.size(); i++){ // 팀 생성
                    HashMap<String, Object> teamMap = new HashMap<>();
                    teamMap.put("voteDate", sunday);
                    teamMap.put("teamName", teamName[i]);
                    sqlSession.insert("TeamBuilderMapper.createTeam", teamMap);
                    for (int j = 0; j < teams.get(i).size(); j++){ // 해당 팀의 팀 멤버 생성
                        Long memberId = teams.get(i).get(j).getMemberId();
                        HashMap<String, Object> teamMemberMap = new HashMap<>();
                        teamMemberMap.put("voteDate", sunday);
                        teamMemberMap.put("teamName", teamName[i]);
                        teamMemberMap.put("memberId", memberId);
                        sqlSession.insert("TeamBuilderMapper.createTeamMember", teamMemberMap);
                    }
                }
            }
        } while (!allEqual); // 배열의 모든 값이 같지 않으면 로직 반복
    }

    // 팀 조회
    @Override
    public List<MemberDto> showTeam(String teamName) {
        LocalDateTime sunday = new VoteDto().getSunday();
        HashMap<String, Object> teamMap = new HashMap<>();
        teamMap.put("teamName", teamName);
        teamMap.put("voteDate", sunday);
        return sqlSession.selectList("TeamBuilderMapper.showTeam", teamMap);
    }   


}
