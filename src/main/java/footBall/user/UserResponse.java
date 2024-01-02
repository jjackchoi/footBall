package footBall.user;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserResponse {

    private int userSeq;
    private String userId;
    private String userPswd;
    private String userName;
    private String userBirth;
    private String userPhone;
    private String userAddress;
    private boolean userAuth;
    private boolean userDelYn;
    private LocalDateTime userRegDate;
    private LocalDateTime userModDate;
}
