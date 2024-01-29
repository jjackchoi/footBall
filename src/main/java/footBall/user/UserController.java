package footBall.user;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

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


    // 회원가입 닉네임 중복검사
    @ResponseBody
    @GetMapping("/checkNickname")
    public ResponseEntity<?>  checkNickname(@RequestBody UserRequest dto){
        log.info(dto.toString());
        System.out.println(dto);
        UserResponse target = userService.getUserByNickname(dto);
        boolean response = false;
        if (target == null){
            response = true;
        }
        return ResponseEntity.ok(response);
    }

    // 회원가입 버튼 클릭 시
    @PostMapping("/signup")
    public String signup(UserRequest dtos){
        userService.userRegister(dtos);
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
        session.setAttribute("userId", id);
        return "redirect:/";
    }

    @GetMapping("/test")
    public String testLayout(){
        return "layout/basic";
    }
}
