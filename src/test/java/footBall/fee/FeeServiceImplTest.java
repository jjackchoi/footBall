package footBall.fee;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class FeeServiceImplTest {

    @Autowired
    private FeeServiceImpl feeService;
    @Test
    void getAllUserFee() {
        List<FeeDto> userFees = feeService.getAllUserFee();
        System.out.println(userFees.toString());
    }
}