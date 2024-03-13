package footBall.fee;

import footBall.fee.FeeDto;
import footBall.user.UserResponse;
import footBall.user.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
public class FeeController {

    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private FeeServiceImpl feeService;

    @GetMapping("/fee")
    public String payment(Model model){
        List<UserResponse> allUser = userService.getAllUser();
        model.addAttribute("posts",allUser);
        return "fee/fee";
    }
    @PostMapping("/fee/check")
    public ResponseEntity<String> handleCheckboxChange(@RequestBody FeeDto checkboxData) {
        int success = feeService.feeInsert(checkboxData);

        return new ResponseEntity<>("Data received successfully", HttpStatus.OK);
    }
}
