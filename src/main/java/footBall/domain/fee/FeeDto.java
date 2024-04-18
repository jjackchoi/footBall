package footBall.domain.fee;

import lombok.Data;

@Data
public class FeeDto {
    private int feeId;      // 회비 id
    private int fbUserId;  // 유저 id
    private int feeMonth;   // 회비 납부 월
    private String feeAnnualPaymentYn; // 회비 일괄 납부
    private int rowIndex;
    private Boolean isChecked;
}
