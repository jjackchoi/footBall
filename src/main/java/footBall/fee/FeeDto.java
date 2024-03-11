package footBall.fee;

import lombok.Data;

@Data
public class FeeDto {
    private int fee_id;      // 회비 id
    private int fb_user_id;  // 유저 id
    private int fee_month;   // 회비 납부 월
}
