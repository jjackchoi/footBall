package footBall.user;

import java.util.List;

public interface UserService {
    // 회원가입
    int userRegister(UserRequest userRequest);
    // 유저 전체조회
    List<UserResponse> getAllUser();
    // 이메일로 유저 조회
    UserResponse getUserByEmail(UserRequest dto);
    // 로그인
    Long login(UserRequest dto);
}
