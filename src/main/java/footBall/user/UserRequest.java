package footBall.user;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserRequest {

    private Long FB_USER_ID;                 //	유저 ID
    private String FB_USER_EMAIL;            //	유저 이메일
    private String FB_USER_PSWD;             //	유저 비밀번호
    private String FB_USER_NICKNAME;         //	유저 닉네임
    private String FB_USER_NAME;             //	유저 이름
    private String FB_USER_BIRTH;            //	유저 생년월일
    private String FB_USER_PHONE;            //	유저 휴대폰번호
    private String FB_USER_ADDRESS;          //	유저 주소

}
