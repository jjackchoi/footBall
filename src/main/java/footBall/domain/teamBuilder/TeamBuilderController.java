package footBall.domain.teamBuilder;

import footBall.domain.attendee.AttendeeService;
import footBall.domain.member.MemberDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class TeamBuilderController {

    private final TeamBuilderService teamBuilderService;
    private final AttendeeService attendeeService;

    @GetMapping("/teamBuilder")
    public String index(Model model){
        List<MemberDto> attendees = teamBuilderService.getAttendee(); // 투표에서 참석한 인원 불러오기
        model.addAttribute("attendees", attendees);
        return "teamBuilder/teamBuilder";
    }
}
