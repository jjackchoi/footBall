package footBall.freeBoardComment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class FbcController {
    @Autowired
    private FbcServiceImpl fbcService;
}
