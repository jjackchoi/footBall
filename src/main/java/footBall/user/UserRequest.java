package footBall.user;

import lombok.Data;

@Data
public class UserRequest {

    private int userSeq; // 유저시퀀스
    private String userId; // 유저 아이디
    private String userPswd; // 유저 비밀번호
    private String userName; // 유저 이름
    private String userBirth; // 유저 생년월일
    private String userAddress; // 유저 주소
    private String userPhone; // 유저 폰번호

}
