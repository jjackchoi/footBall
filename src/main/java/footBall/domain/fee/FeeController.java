package footBall.domain.fee;

import footBall.domain.user.UserResponse;
import footBall.domain.user.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class FeeController {

    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private FeeServiceImpl feeService;

    @GetMapping("/fee")
    public String payment(Model model){
        List<UserResponse> allMember = userService.getAllMember();
        model.addAttribute("posts",allMember);
        return "fee/fee";
    }

    // 회비 체크박스 체크 시
    @ResponseBody
    @PostMapping("/fee/check")
    public ResponseEntity<String> handleCheckboxChange(@RequestBody FeeDto checkboxData) {
        int insertCount = 0;
        int deleteCount = 0;
        if(checkboxData.getIsChecked().equals(true)){
            insertCount = feeService.createFee(checkboxData); // 회비 납부 o
        }else {
            deleteCount = feeService.deleteFee(checkboxData); // 회비 납부 x
        }
        return new ResponseEntity<>("Data received successfully", HttpStatus.OK);
    }

    // 모든 회비정보 불러오기
    @ResponseBody
    @GetMapping("/fee/check")
    public ResponseEntity<List<FeeDto>> getAllFee(){
        List<FeeDto> userFees = feeService.getAllUserFee();
        return ResponseEntity.status(HttpStatus.OK).body(userFees);
    }
}
