package footBall.fee;

import footBall.domain.fee.FeeDto;
import footBall.domain.fee.FeeServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

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