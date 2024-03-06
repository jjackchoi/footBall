package footBall.attendee;

import footBall.user.UserResponse;
import footBall.user.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class AttendeeController {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private AttendeeServiceImpl attendeeService;

    @GetMapping("/weeklyAttendee")
    public String weeklyAttendee(Model model){
        List<UserResponse> users = userService.getAllUser();
        model.addAttribute("users", users);
        return "weeklyAttendee/weeklyAttendee";
    }

    @ResponseBody
    @PostMapping("/weeklyAttendee/voteDate")
    public String dateCreate(@RequestBody AttendeeDto params){
        int created = attendeeService.createDate(params);
        if(created == 1) {
            return "success";
        }else {
            return "fail";
        }
    }


}
