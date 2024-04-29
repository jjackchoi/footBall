package footBall.domain.introduction;

import footBall.domain.user.UserRequest;
import footBall.domain.user.UserResponse;
import footBall.domain.user.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class IntroductionController {

    private final UserServiceImpl userService;

    // 멤버 소개 페이지
    @GetMapping("/introduction/member")
    public String introducingMembers(Model model){
        List<UserResponse> members = userService.getAllMember();
        model.addAttribute("members", members);
        return "introduction/member";
    }

    // 멤버 정보 기입 및 수정
    @ResponseBody
    @PostMapping("/introduction/member")
    public ResponseEntity<String> insertMemInfo(@RequestBody UserRequest params){
        userService.insertMemInfo(params);
        return ResponseEntity.status(HttpStatus.OK).body("success");
    }

    // 드림FC 소개
    @GetMapping("/introduction/club")
    public String introducingClub (){
        return "introduction/club";
    }

}
