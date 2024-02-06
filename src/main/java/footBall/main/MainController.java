package footBall.main;

import footBall.freeBoard.FreeBoardResponse;
import footBall.freeBoard.FreeBoardService;
import footBall.suggestionBoard.SuggestionBoardResponse;
import footBall.suggestionBoard.SuggestionBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MainController {

    @GetMapping("/")
    public String index(){
        return "main";
    }

}
