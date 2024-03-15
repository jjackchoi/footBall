package footBall.fee;

import java.util.List;

public interface FeeService {

    // 회비 납부 o
    int createFee(FeeDto checkboxData);

    // 회비 납부 x
    int deleteFee(FeeDto checkboxData);

    // 모든 회비정보 불러오기
    List<FeeDto> getAllUserFee();
}
