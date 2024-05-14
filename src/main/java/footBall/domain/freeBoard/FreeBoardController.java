package footBall.domain.freeBoard;

import footBall.common.co.Criteria;
import footBall.common.co.PageDto;
import footBall.domain.freeBoardComment.FbcResponse;
import footBall.domain.freeBoardComment.FbcService;
import footBall.domain.user.UserResponse;
import footBall.domain.user.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class FreeBoardController {
    @Autowired
    private FreeBoardService freeBoardService;

    @Autowired
    private UserService userService;
    @Autowired
    private FbcService fbcService;


    // 자유게시판 페이지
    @GetMapping("/freeBoard")
    public String index(@ModelAttribute Criteria cri,
                        Model model) {
            if(cri.getKeyword() != null){
                Criteria criteria = new Criteria(cri.getPageNum(),cri.getAmount(),cri.getKeyword());
                List<FreeBoardResponse> list = freeBoardService.searchBoard(criteria);
                List<FreeBoardResponse> posts = freeBoardService.getCommentsCount(list);
                model.addAttribute("posts", posts);
                model.addAttribute("maker", new PageDto(criteria, freeBoardService.allSeachCount(cri.getKeyword())));
                model.addAttribute("keyword", cri.getKeyword());
            }else {
                Criteria criteria = new Criteria(cri.getPageNum(),cri.getAmount());
                List<FreeBoardResponse> list = freeBoardService.findAll(criteria);
                List<FreeBoardResponse> posts = freeBoardService.getCommentsCount(list);
                model.addAttribute("posts",posts);
                model.addAttribute("maker", new PageDto(criteria, freeBoardService.allCount()));
            }

        return "freeBoard/freeBoard";
    }

    // 글 작성 및 수정
    @ResponseBody
    @PostMapping("/freeBoard/save")
    public String save(HttpSession session, FreeBoardRequest dto){
        // 세션의 유저아이디로 유저정보 불러오기
        int userId = (Integer) session.getAttribute("userId");
        UserResponse entity = userService.findOne(userId);
        // dto에 freeBoardId가 없을 때 글 생성
        if(dto.getFreeBoardId() == 0){
            dto.setFbUserId(userId);
            dto.setFreeBoardAuthor(entity.getFbUserNickname());
            int created = freeBoardService.boardCreate(dto);
            if(created != 0) {
                return "success";
            }else{
                return "false";
            }
        }else{ // dto에 freeBoardId가 있을 때 글 수정
            int updated = freeBoardService.boardUpdate(dto);
            if(updated != 0) {
                return "success";
            }else{
                return "false";
            }
        }

    }

    // 글 삭제
    @PostMapping("/freeBoard/delete")
    public String deleteOne(int freeBoardId){
        List<FbcResponse> comments = fbcService.findCommentsById(freeBoardId);
        if(comments.size() == 0) { // 댓글 없을 시 바로 삭제
            freeBoardService.deleteOne(freeBoardId);
            return "redirect:/freeBoard";
        }
        // 댓글데이터 있을시에 삭제하고 넘어감
        fbcService.deleteComments(freeBoardId);
        freeBoardService.deleteOne(freeBoardId);
        return "redirect:/freeBoard";
    }

}
