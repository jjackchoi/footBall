package footBall.domain.member;

import footBall.domain.user.UserResponse;
import footBall.domain.user.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class MemberController {

    private final UserServiceImpl userService;
    private final MemberServiceImpl memberService;

    // 멤버 승인 페이지
    @GetMapping("/member/authority")
    public String authority(Model model, @RequestParam(required = false) String keyword , @RequestParam(required = false) String membersOnly) {
        List<UserResponse> user;
        if (keyword != null) {
            user = userService.getSearchUser(keyword);
        } else if(membersOnly != null) {
            if(membersOnly.equals("true")){
                user = userService.getAllMember();
            }else{
                user = userService.getGest();
            }
        }else {
            user = userService.getAllUser();
        }
        model.addAttribute("posts", user);
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

    // 멤버 능력치 적용 및 평균 계산
    @ResponseBody
    @PostMapping("/member/abilitySetup")
    public ResponseEntity<MemberDto> applyAbility(@RequestBody MemberDto params){
        memberService.applyAbility(params);
        return ResponseEntity.status(HttpStatus.OK).body(memberService.getMemberByUserId(params));
    }



}
