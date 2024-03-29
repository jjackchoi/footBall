package footBall.domain.fee;

import lombok.Data;

@Data
public class FeeDto {
    private int feeId;      // 회비 id
    private int fbUserId;  // 유저 id
    private int feeMonth;   // 회비 납부 월
    private int rowIndex;
    private Boolean isChecked;
}
