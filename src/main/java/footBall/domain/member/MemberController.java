package footBall.domain.member;

import footBall.domain.user.UserResponse;
import footBall.domain.user.UserServiceImpl;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final UserServiceImpl userService;
    private final MemberServiceImpl memberService;

    // 멤버 승인 페이지
    @GetMapping("/member/authority")
    public String authority(Model model){
        List<UserResponse> allUser = userService.getAllUser();
        model.addAttribute("posts",allUser);
        return "member/authority";
    }

    // 멤버 여부 체크 후 화면에 체크 렌더링
    @ResponseBody
    @GetMapping("/member/authority/check")
    public ResponseEntity<List<UserResponse>> getAllFee(){
        List<UserResponse> user = userService.getAllUser();
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    // 체크 여부에 따라 멤버 여부 업데이트
    @PostMapping("/member/authority/grant")
    public String grantAuthority(@RequestParam("fbUserId") String fbUserId, @RequestParam("isChecked") boolean isChecked) {
        int parsedUserId = Integer.parseInt(fbUserId);
        if (isChecked) { // 체크 시 멤버 여부 Y로 업데이트 및 멤버 데이터 생성
            memberService.grantAuthority(parsedUserId);
        } else { // 체크 해제 시 멤버 여부 N으로 업데이트 멤버 데이터 삭제
            memberService.revokeAuthority(parsedUserId);
        }
        return "redirect:/member/authority";
    }

    // 멤버 능력치 설정 페이지
    @GetMapping("/member/abilitySetup")
    public String abilitySetup(Model model){
        List<MemberDto> members = memberService.getMembers();
        model.addAttribute("members", members);
        return "member/abilitySetup";
    }



}
