package footBall.domain.user;

import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.thymeleaf.util.StringUtils;

@Data
public class UserRequest {

    private int fbUserId;            // 유저 ID
    private String fbUserEmail;      //	유저 이메일
    private String fbUserPswd;       //	유저 비밀번호
    private String fbUserNickname;   //	유저 닉네임
    private String fbUserName;       //	유저 이름
    private String fbUserBirth;      //	유저 생년월일
    private String fbUserPhone;      //	유저 휴대폰번호
    private String fbUserAddress;    //	유저 주소
    private String fbUserSpecialty;  // 유저 특기
    private String fbUserMainPosition;    // 유저 주포지션
    private String fbUserImg;        // 유저 이미지

    public void encodingPassword(PasswordEncoder passwordEncoder){
        if(StringUtils.isEmpty(fbUserPswd)){
            return;
        }
        fbUserPswd = passwordEncoder.encode(fbUserPswd);
    }

}
