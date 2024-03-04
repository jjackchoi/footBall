package footBall.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class AttendeeController {

    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/weeklyAttendee")
    public String weeklyAttendee(Model model){
        List<UserResponse> users = userService.getAllUser();
        model.addAttribute("users", users);
        return "weeklyAttendee/weeklyAttendee";
    }


}
