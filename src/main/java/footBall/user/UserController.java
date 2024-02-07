package footBall.user;

import jakarta.servlet.http.HttpSession;
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
public class UserController {

    @Autowired
    private UserServiceImpl userService;

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
    public String signup(UserRequest dtos){
        userService.userRegister(dtos);
        log.info(dtos.toString());
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
    public String loginDo(UserRequest dto, HttpSession session){
        log.info(dto.toString());
        int id = userService.login(dto);
        if(id == 0){ // 로그인 실패 시
            return "redirect:/login";
        }

        String fbUserNickname = userService.findNickname(id);
        session.setAttribute("userId", id);
        session.setAttribute("userNickname", fbUserNickname);
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

    // 내정보화면
    @GetMapping("/myPage")
    public String myPage(HttpSession session, Model model){
        UserResponse userResponse = userService.findOne((Integer) session.getAttribute("userId"));
        System.out.println(userResponse);
        model.addAttribute("user",userResponse);
        return "myPage";
    }

    // 메인화면

    @GetMapping("/test")
    public String testLayout(){
        return "layout/basic";
    }
}
