package footBall.suggestionBoard;

import footBall.freeBoard.FreeBoardRequest;
import footBall.user.UserResponse;
import footBall.user.UserServiceImpl;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class SuggestionBoardController {
    @Autowired
    private SuggestionBoardServiceImpl suggestionBoardService;

    @Autowired
    private UserServiceImpl userService;

    // 건의게시판 페이지
    @GetMapping("/suggestionBoard")
    public String index(Model model, HttpSession session){
        if (session.getAttribute("userId") != null) {
            List<SuggestionBoardResponse> posts = suggestionBoardService.findAll();
            model.addAttribute("posts", posts);
        }
        return "suggestionBoard/suggestionBoard";
    }

    // 글 작성
    @ResponseBody
    @PostMapping("/suggestionBoard/create")
    public String create(HttpSession session, SuggestionBoardRequest dto){
        UserResponse entity = userService.findOne((Integer) session.getAttribute("userId"));
        dto.setFbUserId((Integer) session.getAttribute("userId"));
        dto.setSuggestionBoardAuthor(entity.getFbUserNickname());
        int Success = suggestionBoardService.create(dto);
        if(Success != 0){
            return "success";
        }else {
            return "false";
        }
    }

    // 건의게시판 제목클릭 시 상세보기
    @GetMapping("/suggestionBoard/{id}")
    public String detail(@PathVariable int id, Model model){
        SuggestionBoardResponse post = suggestionBoardService.findById(id);
        model.addAttribute("post", post);
        return "suggestionBoard/suggestionBoardDetails";
    }
}
