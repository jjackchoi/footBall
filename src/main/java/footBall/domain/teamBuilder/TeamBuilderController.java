package footBall.domain.teamBuilder;

import footBall.domain.attendee.AttendeeService;
import footBall.domain.user.UserResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class TeamBuilderController {

    private final TeamBuilderService teamBuilderService;
    private final AttendeeService attendeeService;

    // 팀 구성 페이지
    @GetMapping("/teamBuilder")
    public String index(Model model){
        List<UserResponse> attendees = teamBuilderService.getAttendee(); // 투표에서 참석한 인원 불러오기
        model.addAttribute("attendees", attendees);
        return "teamBuilder/teamBuilder";
    }

    // 팀 섞기
    @ResponseBody
    @PostMapping("/teamBuilder/shuffleTeams")
    public ResponseEntity<String> shuffleTeams(@RequestParam Long numberOfTeams){
        // 투표에서 참석한 인원 불러오기
        List<UserResponse> attendees = teamBuilderService.getAttendee();
        // 팀 섞기
        teamBuilderService.shuffleTeams(numberOfTeams, attendees);
        return ResponseEntity.status(HttpStatus.OK).body("success");
    }

    // 팀 멤버 조회
    @ResponseBody
    @GetMapping("/teamBuilder/shuffleTeams")
    public ResponseEntity<List<UserResponse>> showTeam(@RequestParam String teamName){
        List<UserResponse> members = teamBuilderService.showTeam(teamName);
        return ResponseEntity.status(HttpStatus.OK).body(members);
    }

    // 팀 내용 조회
    @ResponseBody
    @GetMapping("/teamBuilder/teams")
    public ResponseEntity<List<TeamDto>> getTeams(){
        return ResponseEntity.status(HttpStatus.OK).body(teamBuilderService.getTeams());
    }

}
