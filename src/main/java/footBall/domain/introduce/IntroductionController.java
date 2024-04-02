package footBall.domain.introduce;

import footBall.domain.fee.FeeDto;
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
    public String introduceMember(Model model){
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

    @GetMapping("/introduction/authority")
    public String authority(Model model){
        List<UserResponse> allUser = userService.getAllUser();
        model.addAttribute("posts",allUser);
        return "introduction/authority";
    }

    @PostMapping("/introduction/authority/grant")
    public String grantAuthority(@RequestParam("fbUserId") String fbUserId, @RequestParam("isChecked") boolean isChecked) {
        int parsedUserId = Integer.parseInt(fbUserId);
        if (isChecked) {
            userService.grantAuthority(parsedUserId);
        } else {
            userService.revokeAuthority(parsedUserId);
        }
        return "redirect:/introduction/authority";
    }
    @ResponseBody
    @GetMapping("/introduction/authority/check")
    public ResponseEntity<List<UserResponse>> getAllFee(){
        List<UserResponse> user = userService.getAllUser();
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

}
