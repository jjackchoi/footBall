package footBall.suggestionBoard;

import footBall.user.UserResponse;
import footBall.user.UserServiceImpl;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class SuggestionBoardController {
    @Autowired
    private SuggestionBoardServiceImpl suggestionBoardService;

    @Autowired
    private UserServiceImpl userService;

    /*건의게시판 페이지*/
    @GetMapping("/suggestionBoard")
    public String index(Model model, HttpSession session){
        return "suggestionBoard/suggestionBoard";
    }

    /*게시글 전체조회*/
    @ResponseBody
    @GetMapping("/suggestionBoard/findAll")
    public List<SuggestionBoardResponse> findAll(){
        return suggestionBoardService.findAll();
    }

    /*게시글 비밀글제외 조회*/
    @ResponseBody
    @GetMapping("/suggestionBoard/findExcludeSec")
    public List<SuggestionBoardResponse> findExcludeSec(){
        return suggestionBoardService.findExcludeSecret();
    }


    /*글 작성*/
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

    /*건의게시판 제목클릭 시 상세보기*/
    @GetMapping("/suggestionBoard/{id}")
    public String detail(@PathVariable int id, Model model){
        SuggestionBoardResponse post = suggestionBoardService.findById(id);
        model.addAttribute("post", post);
        return "suggestionBoard/suggestionBoardDetails";
    }
}
