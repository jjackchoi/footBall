package footBall.attendee;

import footBall.user.UserResponse;
import footBall.user.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Slf4j
public class AttendeeController {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private AttendeeServiceImpl attendeeService;

    // 금주의 참석인원 페이지
    @GetMapping("/weeklyAttendee")
    public String weeklyAttendee(Model model){
        // 미참여(미투표) 인원 조회
        List<UserResponse> nonattendanceUsers = attendeeService.getNonattendanceUser();
        model.addAttribute("nonattendanceUsers", nonattendanceUsers);

        // 투표한 인원 조회
        List<UserResponse> votedUsers = attendeeService.votedUser();
        model.addAttribute("votedUsers", votedUsers);
        return "weeklyAttendee/weeklyAttendee";
    }

    // 투표 대상 날짜 데이터 넣기
    @ResponseBody
    @PostMapping("/weeklyAttendee/voteDate")
    public String createDate(@RequestBody VoteDto params){
        int created = attendeeService.createDate(params);
        if(created == 1) {
            return "success";
        }else {
            return "fail";
        }
    }

    // 투표 대상 날짜 데이터 삭제
    @ResponseBody
    @DeleteMapping("/weeklyAttendee/voteDate")
    public String deleteDate(@RequestBody VoteDto params){
       int deleted = attendeeService.deleteDate(params);
       if (deleted > 0) {
           return "success";
       } else {
           return "fail";
       }
    }

    // 투표 대상 데이터 존재여부 판별
    @ResponseBody
    @GetMapping("/weeklyAttendee/voteDate")
    public String findDate(){
        int found = attendeeService.findDate();
        if (found > 0){
            return "existent";
        } else {
            return "nonExistent";
        }
    }

}
