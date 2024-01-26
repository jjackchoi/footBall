package footBall.user;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@Slf4j
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    // 회원가입 페이지
    @GetMapping("/signup")
    public String register(){
        return "signup";
    }

    // 로그인 페이지
    @GetMapping("/login")
    public String login(HttpSession session){
        Long id = (Long) session.getAttribute("userId");
        if(id != null){
            return "redirect:/";
        }
        return "login";
    }

    // 로그인 버튼 클릭 시
    @PostMapping("/login")
    public String loginDo(UserRequest dto, HttpSession session){
        log.info(dto.toString());
        Long id = userService.login(dto);
        if(id == null){ // 로그인 실패 시
            return "redirect:/login";
        }
        session.setAttribute("userId", id);
        return "redirect:/";
    }

}
