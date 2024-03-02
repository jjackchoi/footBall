package footBall.user;

import org.apache.ibatis.annotations.Arg;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;

@Controller
public class AttendeeController {
    @GetMapping("/weeklyAttendee")
    public String weeklyAttendee(){
        return "weeklyAttendee/weeklyAttendee";
    }


}
