package footBall.domain.my;

import footBall.domain.freeBoard.FreeBoardResponse;
import footBall.domain.freeBoard.FreeBoardService;
import footBall.domain.suggestionBoard.SuggestionBoardResponse;
import footBall.domain.suggestionBoard.SuggestionBoardService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MyController {
    @Autowired
    private FreeBoardService freeBoardService;
    @Autowired
    private SuggestionBoardService suggestionBoardService;

    //내가쓴글 조회
    @GetMapping("/board/myWrite")
    public String myWrite(Model model, HttpSession session){
        List<FreeBoardResponse> posts = freeBoardService.findByUserId((Integer) session.getAttribute("userId")); //자유게시판 내가쓴글조회
        List<SuggestionBoardResponse> subPosts = suggestionBoardService.findByUserId((Integer) session.getAttribute("userId")); //건의게시판 내가쓴글조회
        model.addAttribute("posts", posts);
        model.addAttribute("subPosts", subPosts);
        return "my/myWrite";
    }


}
