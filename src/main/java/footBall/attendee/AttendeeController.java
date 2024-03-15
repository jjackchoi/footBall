package footBall.attendee;

import footBall.user.UserResponse;
import footBall.user.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
        List<UserResponse> nonattendanceUsers = attendeeService.findNonattendanceUser();
        model.addAttribute("nonattendanceUsers", nonattendanceUsers);

        // 투표한 인원 조회
        List<UserResponse> votedUsers = attendeeService.findVotedUser();
        model.addAttribute("votedUsers", votedUsers);
        return "weeklyAttendee/weeklyAttendee";
    }

    // 미참여(미투표) 인원 조회
    @ResponseBody
    @GetMapping("/weeklyAttendee/findNonattendanceUser")
    public List<UserResponse> findNonattendanceUser(){
        return attendeeService.findNonattendanceUser();
    }

    // 투표한 유저 찾기
    @ResponseBody
    @GetMapping("/weeklyAttendee/findVotedUser")
    public List<UserResponse> findVotedUser(){
        return attendeeService.findVotedUser();
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
        // 투표 대상 날짜 데이터의 id에 종속되어있는 참석 데이터 삭제
        attendeeService.deleteAttend(params);
        // 참석 데이터 삭제가 완료되면 날짜 데이터 삭제
        int deleted = attendeeService.deleteDate(params);
        if (deleted > 0){
            return "success";
        }else {
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

    // 투표 값 업데이트
    @ResponseBody
    @PostMapping("/weeklyAttendee/updateVote")
    public String updateVote(@RequestBody AttendDto params){
        int updated = attendeeService.updateVote(params);
        if (updated > 0){
            return "success";
        }else{
            return "fail";
        }
    }

    // 미참여인원 투표
    @ResponseBody
    @PostMapping("/weeklyAttendee/createVote")
    public String createVote(@RequestBody AttendDto params){
        int created = attendeeService.createVote(params);
        if (created > 0){
            return "success";
        }else{
            return "fail";
        }
    }

    // 투표한 해당 유저의 참석여부 값 가져오기
    @ResponseBody
    @GetMapping("/weeklyAttendee/getAttendStatus/{fbUserId}")
    public ResponseEntity<String> getAttendStatus(@PathVariable Long fbUserId){
        log.info(String.valueOf(fbUserId));
        return ResponseEntity.status(HttpStatus.OK).body(attendeeService.getAttendStatus(fbUserId));
    }
}
