package footBall.freeBoard;

import footBall.freeBoardComment.FbcResponse;
import footBall.freeBoardComment.FbcService;
import footBall.user.UserResponse;
import footBall.user.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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
    public String index(@RequestParam(name = "page", defaultValue = "1") int page,
                        Model model,
                        HttpSession session) {
        if (session.getAttribute("userId") != null) {
            int pageSize = 10; // 페이지당 데이터 수
            int totalDataCount = freeBoardService.allCount(); // 총 데이터 수
            int totalPages = (int) Math.ceil((double) totalDataCount / pageSize); // 전체 페이지 수

            // 현재 페이지에 따른 OFFSET 계산
            int offset = (page - 1) * pageSize;

            List<FreeBoardResponse> posts;

            if (totalDataCount == 0) {
                // 데이터가 없는 경우
                posts = Collections.emptyList();
            } else if (page == totalPages) {
                // 마지막 페이지의 경우 나머지 데이터 수만큼만 가져오도록 설정
                int remainingDataCount = totalDataCount % pageSize;
                if (remainingDataCount == 0) {
                    remainingDataCount = pageSize;
                }

                Map<String, Integer> params = new HashMap<>();
                params.put("offset", offset);
                params.put("pageSize", remainingDataCount);

                posts = freeBoardService.findPaginatedData(params);
            } else {
                // 그 외 페이지는 pageSize만큼 데이터를 가져옴
                Map<String, Integer> params = new HashMap<>();
                params.put("offset", offset);
                params.put("pageSize", pageSize);

                posts = freeBoardService.findPaginatedData(params);
            }

            model.addAttribute("posts", posts);
            model.addAttribute("currentPage", page);
            model.addAttribute("totalPages", totalPages);
        }
        return "freeBoard/freeBoard";
    }

    // 게시글 전체 조회
    @GetMapping("/freeBoard/allSearch")
    public ResponseEntity<List<FreeBoardResponse>> searchFreeBoardPosts() {
        List<FreeBoardResponse> searchResult = freeBoardService.findAll();
        return ResponseEntity.ok().body(searchResult);
    }

    // 검색 결과 조회
    @GetMapping("/freeBoard/search")
    public ResponseEntity<List<FreeBoardResponse>> searchFreeBoardPosts(@RequestParam(name = "freeBoardTitle") String freeBoardTitle,HttpSession session) {
        List<FreeBoardResponse> searchResult = new ArrayList<FreeBoardResponse>();
        if(session.getAttribute("userId") != null){
            searchResult = freeBoardService.searchFreeBoardPosts(freeBoardTitle);
        }
        return ResponseEntity.ok().body(searchResult);
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

    // 글 단 건조회
    @GetMapping("/freeBoard/details/{id}")
    public String boardOne(@PathVariable int id, Model model){
        FreeBoardResponse param = freeBoardService.findOne(id);
        List<FbcResponse> comment = fbcService.findList(id);
        model.addAttribute("post",param);
        model.addAttribute("comments",comment);
        return "freeBoard/freeBoardDetails";
    }
    // 글 삭제
    @PostMapping("/freeBoard/delete")
    public String deleteOne(int freeBoardId){
        List<FbcResponse> list = fbcService.findList(freeBoardId);
        if(list.size() == 0) { // 댓글 없을 시 바로 삭제
            freeBoardService.deleteOne(freeBoardId);
            return "redirect:/freeBoard";
        }
        // 댓글데이터 있을시에 삭제하고 넘어감(종속되어있어서 fk)
        fbcService.deleteList(freeBoardId);
        freeBoardService.deleteOne(freeBoardId);
        return "redirect:/freeBoard";
    }

}
