package footBall.domain.suggestionBoard;

import footBall.domain.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class SbcController {

    private final SbcService sbcService;
    private final UserService userService;

    // 답변 등록 및 해당 게시글 답변 완료 상태로 변경
    @ResponseBody
    @PostMapping("/suggestionBoard/comments")
    public ResponseEntity<String> createSbc(SbcRequest params){
        sbcService.createSbc(params);
        return ResponseEntity.status(HttpStatus.OK).body("success");
    }

    // 해당 게시글 답변 조회
    @ResponseBody
    @GetMapping("/suggestionBoard/comments/{id}")
    public ResponseEntity<SbcResponse> getComment(@PathVariable int id){
        return ResponseEntity.status(HttpStatus.OK).body(sbcService.getComment(id));
    }
}
