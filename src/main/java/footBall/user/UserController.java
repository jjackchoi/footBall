package footBall.user;

import footBall.util.MailDto;
import footBall.util.MailService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl userService;
    private final MailService mailService;

    // 회원가입 페이지
    @GetMapping("/signup")
    public String signupPage(){
        return "signup";
    }

    // 회원가입 이메일 중복검사
    @ResponseBody
    @GetMapping("/checkEmail")
    public ResponseEntity<Boolean> checkEmail(String email){
        log.info(email);
        List<UserResponse> target = userService.getUserByEmail(email);
        if (target.size() >= 1){
            return ResponseEntity.status(HttpStatus.OK).body(false);
        }else {
            return ResponseEntity.status(HttpStatus.OK).body(true);
        }
    }

    // 회원가입 닉네임 중복검사
    @ResponseBody
    @GetMapping("/checkNickname")
    public ResponseEntity<Boolean> checkNickname(String nickname){
        log.info(nickname);
        List<UserResponse> target = userService.getUserByNickname(nickname);
        if (target.size() >= 1){
            return ResponseEntity.status(HttpStatus.OK).body(false);
        }else {
            return ResponseEntity.status(HttpStatus.OK).body(true);
        }
    }

    // 회원가입 버튼 클릭 시
    @PostMapping("/signup")
    public String signup(UserRequest params){
        userService.userRegister(params);
        log.info(params.toString());
        return "redirect:/login";
    }

    // 로그인 페이지
    @GetMapping("/login")
    public String login(HttpSession session){
        Integer id = (Integer) session.getAttribute("userId");
        if(id != null){
            return "redirect:/";
        }
        return "login";
    }

    // 로그인 버튼 클릭 시
    @PostMapping("/login")
    public String loginDo(UserRequest dto, HttpServletRequest request){
        // 1. 로그인 입력 값으로 회원 id 조회
        log.info(dto.toString());
        int id = userService.login(dto);
        if(id == 0){ // 로그인 실패 시
            return "redirect:/login";
        }

        // 2. 세션에 회원 정보 저장 & 세션 유지 시간 설정
        UserResponse userInfo = userService.findOne(id);
        HttpSession session = request.getSession();
        session.setAttribute("userId", id);
        session.setAttribute("userNickname", userInfo.getFbUserNickname());
        session.setAttribute("userAuth", userInfo.getFbUserAuth());
        session.setMaxInactiveInterval(60 * 30);
        return "redirect:/";
    }

    // 로그아웃 시
    @GetMapping("/logout")
    public String logOut(HttpSession session){
        if((Integer)session.getAttribute("userId") != null){
            session.invalidate();
            return "login";
        }else {
            return "login";
        }
    }

    // 비밀번호 찾기 페이지
    @GetMapping("/findPassword")
    public String findPassword(){
        return "findPassword";
    }

    // 인증 번호 발송
    @ResponseBody
    @PostMapping("/findPassword/sendAuth")
    public String sendAuth(@RequestBody MailDto param){
        param.setTitle("[뿟볼] 인증번호 발송");
        param.setContent("인증번호 입니다. \n" +
                mailService.createRandomPw() + "를 입력해주세요!");
        mailService.sendSimpleEmail(param);
        return "success";
    }


    // 내 정보 화면
    @GetMapping("/myPage")
    public String myPage(HttpSession session, Model model){
        UserResponse userResponse = userService.findOne((Integer) session.getAttribute("userId"));
        System.out.println(userResponse);
        model.addAttribute("user",userResponse);
        return "myPage";
    }
}
