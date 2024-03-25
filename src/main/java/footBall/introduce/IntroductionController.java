package footBall.introduce;

import footBall.user.UserResponse;
import footBall.user.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class IntroductionController {

    private final UserServiceImpl userService;

    @GetMapping("/introduction/member")
    public String introduceMember(Model model){
        List<UserResponse> members = userService.getAllMember();
        model.addAttribute("members", members);
        return "introduction/member";
    }

}
