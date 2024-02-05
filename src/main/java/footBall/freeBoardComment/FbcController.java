package footBall.freeBoardComment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class FbcController {
    @Autowired
    private FbcServiceImpl fbcService;

    @PostMapping("/freeBoard/fbcCreate")
    public String fbcCreate(FbcRequest dto){
        int success = fbcService.insert(dto);
        if(success > 0) {
            System.out.println("성공");
        }
        return "redirect:/freeBoard/details/" + dto.getFreeBoardId();
    }
}
