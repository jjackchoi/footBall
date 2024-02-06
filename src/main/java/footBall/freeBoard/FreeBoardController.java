package footBall.freeBoard;

import footBall.freeBoardComment.FbcResponse;
import footBall.freeBoardComment.FbcService;
import footBall.user.UserResponse;
import footBall.user.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class FreeBoardController {
    @Autowired
    private FreeBoardServiceImpl freeBoardService;

    @Autowired
    private UserService userService;
    @Autowired
    private FbcService fbcService;


    // 자유게시판 페이지
    @GetMapping("/freeBoard")
    public String index(Model model,HttpSession session){
        if(session.getAttribute("userId") != null){
            List<FreeBoardResponse> posts = freeBoardService.findAll();
            model.addAttribute("posts", posts);
        }
        return "freeBoard/freeBoard";
    }

    // 글 작성
    @ResponseBody
    @PostMapping("/freeBoard/create")
    public String create(HttpSession session,FreeBoardRequest dto){
        List<UserResponse> entityList = userService.findOne((Integer) session.getAttribute("userId"));
        dto.setFbUserId((Integer) session.getAttribute("userId"));
        dto.setFbUserId(entityList.get(0).getFbUserId());
        dto.setFreeBoardAuthor(entityList.get(0).getFbUserNickname());
        int Success = freeBoardService.boardCreate(dto);
        if(Success != 0){
            return "success";
        }else {
            return "false";
        }
    }
    // 글 수정
    @ResponseBody
    @PostMapping("/freeBoard/update")
    public String update(HttpSession session,FreeBoardRequest dto){
        List<UserResponse> entityList = userService.findOne((Integer) session.getAttribute("userId"));
        dto.setFreeBoardId(dto.getFreeBoardId());
        dto.setFbUserId((Integer) session.getAttribute("userId"));
        dto.setFreeBoardAuthor(entityList.get(0).getFbUserNickname());
        int Success = freeBoardService.boardUpdate(dto);
        if(Success != 0){
            return "success";
        }else {
            return "false";
        }
    }
    // 글 단건조회
    @GetMapping("/freeBoard/details/{id}")
    public String boardOne(@PathVariable int id, Model model){
        FreeBoardResponse param = freeBoardService.findOne(id);
        System.out.println(param);
        List<FbcResponse> comment = fbcService.findList(id);
        model.addAttribute("post",param);
        model.addAttribute("comments",comment);
        return "freeBoard/freeBoardDetails";
    }
    // 글 삭제
    @GetMapping("/freeBoard/delete/{id}")
    public String deleteOne(@PathVariable int id, Model model){
        List<FbcResponse> list = fbcService.findList(id);
        if(list.size() > 0){ //댓글데이터 있을시에 삭제하고 넘어감(종속되어있어서 fk)
            fbcService.deleteList(id);
        }
        int success = freeBoardService.deleteOne(id);
        if(success != 0){
            //성공
        }else {
            //실패
        }
        
        return "redirect:/freeBoard";
    }

}
