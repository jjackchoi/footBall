package footBall.freeBoard;

import footBall.user.UserResponse;
import footBall.user.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class FreeBoardController {
    @Autowired
    private FreeBoardServiceImpl freeBoardService;

    @Autowired
    private UserService userService;

    // 자유게시판 페이지
    @GetMapping("/freeBoard")
    public String index(Model model){
        List<FreeBoardResponse> posts = freeBoardService.findAll();
        model.addAttribute("posts", posts);
        return "freeBoard/freeBoard";
    }
    @ResponseBody
    @PostMapping("/freeBoard/create")
    public String create(HttpSession session,FreeBoardRequest dto){
        List<UserResponse> entityList = userService.findOne((Integer) session.getAttribute("userId"));
        dto.setFbUserId((Integer) session.getAttribute("userId"));
        dto.setFbUserId(entityList.get(0).getFbUserId());
        dto.setFreeBoardAuthor(entityList.get(0).getFbUserNickname());
        int Success = freeBoardService.boardCreate(dto);
        if(Success != 0){
            return "success";
        }else {
            return "false";
        }
    }
}
