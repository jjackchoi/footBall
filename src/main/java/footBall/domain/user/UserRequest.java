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

    // 비밀번호 인코딩
    public void encodingPassword(PasswordEncoder passwordEncoder){
        if(StringUtils.isEmpty(fbUserPswd)){
            return;
        }
        fbUserPswd = passwordEncoder.encode(fbUserPswd);
    }

    // 10자리 난수 생성
    public String generateRandomToken() {
        // 랜덤한 문자열 생성을 위한 문자열
        String candidateChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

        // 랜덤한 토큰 길이
        int length = 10;

        // 랜덤한 토큰 생성
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int index = (int) (Math.random() * candidateChars.length());
            sb.append(candidateChars.charAt(index));
        }
        return sb.toString();
    }

}
