package footBall.domain.previousAttendee;

import footBall.domain.attendee.AttendeeService;
import footBall.domain.attendee.VoteDto;
import footBall.domain.teamBuilder.TeamBuilderService;
import footBall.domain.teamBuilder.TeamDto;
import footBall.domain.user.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class PreviousAttendeeController {

    private final AttendeeService attendeeService;
    private final PreviousAttendeeService previousAttendeeService;
    private final TeamBuilderService teamBuilderService;

    // 지나간 참석자 명단 메인 페이지
    @GetMapping("/previousAttendee")
    public String index(Model model){
        List<VoteDto> votes = previousAttendeeService.getAllVote();
        model.addAttribute("votes", votes);
        return "previousAttendee/previousAttendee";
    }

    // 날짜별 참석자 명단 페이지
    @GetMapping("/previousAttendee/{voteId}")
    public String previousDetail(@PathVariable Long voteId, Model model){
        List<UserResponse> votedUsers = previousAttendeeService.getVotedUsers(voteId);
        VoteDto voteDate = previousAttendeeService.getVoteDate(voteId);
        model.addAttribute("votedUsers", votedUsers);
        model.addAttribute("voteDate", voteDate.getVoteDate());
        return "previousAttendee/previousAttendeeDetails";
    }

    // 날짜별 팀구성 페이지
    @GetMapping("/previousAttendee/{voteId}/teams")
    public String previousTeams(@PathVariable Long voteId, Model model){
        List<UserResponse> attendees = previousAttendeeService.getAttendee(voteId); // 투표에서 참석한 인원 불러오기
        VoteDto voteDate = previousAttendeeService.getVoteDate(voteId); // 투표대상날짜 가져오기
        model.addAttribute("attendees", attendees);
        model.addAttribute("voteDate", voteDate.getVoteDate());
        return "previousAttendee/previousTeams";
    }

    // 날짜별 팀 멤버 조회
    @ResponseBody
    @GetMapping("/previousAttendee/{voteId}/teams/{teamName}")
    public ResponseEntity<List<UserResponse>> showTeam(@PathVariable String teamName, @PathVariable Long voteId){
        List<UserResponse> members = previousAttendeeService.getMemberByTeamName(teamName, voteId);
        return ResponseEntity.status(HttpStatus.OK).body(members);
    }

    // 날짜별 팀 내용 조회
    @ResponseBody
    @GetMapping("/previousAttendee/{voteId}/teams/body")
    public ResponseEntity<List<TeamDto>> getTeams(@PathVariable Long voteId){
        return ResponseEntity.status(HttpStatus.OK).body(previousAttendeeService.getTeams(voteId));
    }

}
