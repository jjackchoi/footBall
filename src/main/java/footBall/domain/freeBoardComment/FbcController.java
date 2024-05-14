package footBall.domain.freeBoardComment;

import footBall.domain.freeBoard.FreeBoardResponse;
import footBall.domain.freeBoard.FreeBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class FbcController {

    private final FbcService fbcService;
    private final FreeBoardService freeBoardService;

    // 글 단건 조회
    @GetMapping("/freeBoard/details/{id}")
    public String boardOne(@PathVariable int id, Model model){
        FreeBoardResponse param = freeBoardService.findOne(id);
        List<FbcResponse> comments = fbcService.findCommentsById(id);
        model.addAttribute("post", param);
        model.addAttribute("comments", comments);
        return "freeBoard/freeBoardDetails";
    }
    
    // 댓글 작성
    @PostMapping("/freeBoard/createFbc")
    public String createComment(FbcRequest dto){
        fbcService.createComment(dto);
        return "redirect:/freeBoard/details/" + dto.getFreeBoardId();
    }
}
