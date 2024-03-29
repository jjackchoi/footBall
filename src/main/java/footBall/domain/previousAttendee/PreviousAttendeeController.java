package footBall.domain.previousAttendee;

import footBall.domain.attendee.AttendeeServiceImpl;
import footBall.domain.attendee.VoteDto;
import footBall.domain.user.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class PreviousAttendeeController {
    @Autowired
    private AttendeeServiceImpl attendeeService;
    @Autowired
    private PreviousAttendeeServiceImpl previousAttendeeService;

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


}
