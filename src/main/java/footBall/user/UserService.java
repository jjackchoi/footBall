package footBall.user;

import java.util.List;

public interface UserService {
    // 회원가입
    int userRegister(UserRequest userRequest);

    // 유저 전체조회
    List<UserResponse> getAllUser();

    // 이메일로 유저 조회
    List<UserResponse> getUserByEmail(String email);

    // 닉네임으로 유저 조회
    List<UserResponse> getUserByNickname(String nickname);

    // 로그인
    int login(UserRequest dto);

    String findNickname(int id);

    List<UserResponse> findOne(Integer id);
}
