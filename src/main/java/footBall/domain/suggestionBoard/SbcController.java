package footBall.domain.suggestionBoard;

import footBall.domain.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SbcController {
    @Autowired
    private SbcService sbcService;

    @Autowired
    private UserService userService;

    @ResponseBody
    @PostMapping("/suggestionBoard/comments")
    public ResponseEntity<String> createSbc(SbcRequest params){
        sbcService.createSbc(params);
        return ResponseEntity.status(HttpStatus.OK).body("success");
    }
}
