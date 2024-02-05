package footBall.suggestionBoard;

import footBall.freeBoard.FreeBoardRequest;
import footBall.user.UserResponse;
import footBall.user.UserServiceImpl;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class SuggestionBoardController {
    @Autowired
    private SuggestionBoardServiceImpl suggestionBoardService;

    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/suggestionBoard")
    public String index(Model model){
        List<SuggestionBoardResponse> posts= suggestionBoardService.findAll();
        model.addAttribute("posts", posts);
        return "suggestionBoard/suggestionBoard";
    }

    // 글 작성
    @ResponseBody
    @PostMapping("/suggestionBoard/create")
    public String create(HttpSession session, SuggestionBoardRequest dto){
        List<UserResponse> entityList = userService.findOne((Integer) session.getAttribute("userId"));
        dto.setFbUserId((Integer) session.getAttribute("userId"));
        dto.setFbUserId(entityList.get(0).getFbUserId());
        dto.setSuggestionBoardAuthor(entityList.get(0).getFbUserNickname());
        int Success = suggestionBoardService.create(dto);
        if(Success != 0){
            return "success";
        }else {
            return "false";
        }
    }
}
