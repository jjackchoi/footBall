package footBall.previousAttendee;

import footBall.attendee.AttendeeServiceImpl;
import footBall.attendee.VoteDto;
import footBall.user.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class PreviousAttendeeController {
    @Autowired
    private AttendeeServiceImpl attendeeService;
    @Autowired
    private PreviousAttendeeServiceImpl previousAttendeeService;

    @GetMapping("/previousAttendee")
    public String index(Model model){
        List<VoteDto> votes = previousAttendeeService.getAllVote();
        model.addAttribute("votes", votes);
        return "previousAttendee/previousAttendee";
    }

}
