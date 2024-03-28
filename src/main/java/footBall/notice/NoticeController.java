package footBall.notice;

import footBall.user.UserResponse;
import footBall.user.UserServiceImpl;
import groovy.util.logging.Slf4j;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class NoticeController {

    private final NoticeServiceImpl noticeService;
    private final UserServiceImpl userService;

    // 공지사항 페이지
    @GetMapping("/notice")
    public String noticeIndex(HttpServletRequest request, Model model){
        List<NoticeResponse> notices = noticeService.getNotices();
        model.addAttribute("notices", notices);
        return "notice/notice";
    }

    // 공지사항 생성
    @ResponseBody
    @PostMapping("/notice")
    public ResponseEntity<String> createNotice(HttpServletRequest request, NoticeRequest params){

        // session 가져오기
        HttpSession session = request.getSession();

        // 세션의 userId로 해당 유저의 정보 가져오기
        UserResponse user = userService.findOne((Integer) session.getAttribute("userId"));

        // params에 작성자 id와 닉네임 세팅
        params.setFbUserId((Integer) session.getAttribute("userId"));
        params.setNoticeAuthor(user.getFbUserNickname());

        // 공지사항 생성
        int success = noticeService.createNotice(params);
        if(success != 0){
            return ResponseEntity.status(HttpStatus.OK).body("created");
        }else {
            return ResponseEntity.status(HttpStatus.OK).body("nonCreated");
        }
    }
}
