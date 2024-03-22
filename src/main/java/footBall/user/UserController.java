package footBall.user;

import footBall.util.MailDto;
import footBall.util.MailService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@Slf4j
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl userService;
    private final MailService mailService;

    // 회원가입 페이지
    @GetMapping("/signup")
    public String signupPage(){
        return "signup";
    }

    // 회원가입 이메일 중복검사
    @ResponseBody
    @GetMapping("/checkEmail")
    public ResponseEntity<Boolean> checkEmail(String email){
        log.info(email);
        List<UserResponse> target = userService.getUserByEmail(email);
        if (target.size() >= 1){
            return ResponseEntity.status(HttpStatus.OK).body(false);
        }else {
            return ResponseEntity.status(HttpStatus.OK).body(true);
        }
    }

    // 회원가입 닉네임 중복검사
    @ResponseBody
    @GetMapping("/checkNickname")
    public ResponseEntity<Boolean> checkNickname(String nickname){
        log.info(nickname);
        List<UserResponse> target = userService.getUserByNickname(nickname);
        if (target.size() >= 1){
            return ResponseEntity.status(HttpStatus.OK).body(false);
        }else {
            return ResponseEntity.status(HttpStatus.OK).body(true);
        }
    }

    // 회원가입 버튼 클릭 시
    @PostMapping("/signup")
    public String signup(UserRequest params){
        userService.userRegister(params);
        log.info(params.toString());
        return "redirect:/login";
    }

    // 로그인 페이지
    @GetMapping("/login")
    public String login(HttpSession session){
        Integer id = (Integer) session.getAttribute("userId");
        if(id != null){
            return "redirect:/";
        }
        return "login";
    }

    // 로그인 버튼 클릭 시
    @PostMapping("/login")
    public String loginDo(UserRequest dto, HttpServletRequest request){
        // 1. 로그인 입력 값으로 회원 id 조회
        log.info(dto.toString());
        int id = userService.login(dto);
        if(id == 0){ // 로그인 실패 시
            return "redirect:/login";
        }

        // 2. 세션에 회원 정보 저장 & 세션 유지 시간 설정
        UserResponse userInfo = userService.findOne(id);
        HttpSession session = request.getSession();
        session.setAttribute("userId", id);
        session.setAttribute("userNickname", userInfo.getFbUserNickname());
        session.setAttribute("userAuth", userInfo.getFbUserAuth());
        session.setMaxInactiveInterval(60 * 30);
        return "redirect:/";
    }

    // 로그아웃 시
    @GetMapping("/logout")
    public String logOut(HttpSession session){
        if((Integer)session.getAttribute("userId") != null){
            session.invalidate();
            return "login";
        }else {
            return "login";
        }
    }

    // 비밀번호 찾기 페이지
    @GetMapping("/findPassword")
    public String findPassword(){
        return "findPassword";
    }

    // 비밀번호 변경 페이지
//    @GetMapping("/findPassword")
//    public String editPassword(@PathVariable String fbUserName, @PathVariable String fbUserEmail) {
//
//    }

    // 인증번호 발송
    @ResponseBody
    @PostMapping("/findPassword/sendAuth")
    public ResponseEntity<String> sendAuth(@RequestBody UserRequest params, HttpSession session){

        // 이름과 이메일로 유저 존재 판별
        if (userService.checkByNameAndEmail(params) == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        // 6자리 난수 생성
        String authNum = mailService.createRandomPw();

        // 이메일로 인증번호 보내기
        MailDto mail = new MailDto();
        mail.setTitle("[뿟볼] 인증번호 발송");
        mail.setRecipient(params.getFbUserEmail());
        mail.setContent("해당 인증번호를 화면에 입력해 주세요! \n" + authNum);
        mailService.sendSimpleEmail(mail);

        // 인증번호과 생성시간 및 만료시간 세션에 추가
        Map<String, Object> authNumMap = new HashMap<>();
        Long createTime = System.currentTimeMillis(); // 인증번호 생성시간
        Long endTime = createTime + (300*1000); // 인증번호 만료시간 300밀리초 * 1000 = 5분

        authNumMap.put("createTime", createTime);
        authNumMap.put("endTime", endTime);
        authNumMap.put("authNum", authNum);

        session.setMaxInactiveInterval(0);
        session.setMaxInactiveInterval(300);
        session.setAttribute("authNum", authNumMap);

        return ResponseEntity.status(HttpStatus.OK).body("success");
    }

    // 인증번호 확인
    @ResponseBody
    @PostMapping("/findPassword/confirmAuth")
    public ResponseEntity<String> confirmAuth(@RequestParam("insertedNum") String insertedNum, HttpSession session){

        // authNum 초기화
        Map<String, Object> authNumMap = new HashMap<>();
        String authNum = "";
        if (session != null){  // session값이 존재 할 때(메일이 발송된 상태일 경우)
            authNumMap = (Map<String, Object>)session.getAttribute("authNum");
            authNum = (String) authNumMap.get("authNum");
            // 일치여부 확인 후 응답
            if (authNum.equals(insertedNum)){
                return ResponseEntity.status(HttpStatus.OK).body("success");
            }else {
                return ResponseEntity.status(HttpStatus.OK).body("fail");
            }
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    // 내 정보 화면
    @GetMapping("/myPage")
    public String myPage(HttpSession session, Model model){
        UserResponse userResponse = userService.findOne((Integer) session.getAttribute("userId"));
        System.out.println(userResponse);
        model.addAttribute("user",userResponse);
        return "myPage";
    }
}
