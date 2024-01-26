package footBall.freeBoard;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class FreeBoardController {
    @Autowired
    private FreeBoardServiceImpl freeBoardService;

    // 자유게시판 페이지
    @GetMapping("/freeBoard")
    public String index(Model model){
        List<FreeBoardResponse> posts = freeBoardService.findAll();
        model.addAttribute("posts", posts);
        return "freeBoard";
    }
}
