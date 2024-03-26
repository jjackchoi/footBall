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

    // 유저 멤버만 조회
    List<UserResponse> getAllMember();

    // 로그인
    int login(UserRequest dto);
    
    // 닉네임 조회
    String findNickname(int id);

    // 정보 조회
    UserResponse findOne(Integer id);

    // 이름과 이메일로 존재여부 판별
    Integer checkByNameAndEmail(UserRequest params);

    // 비밀번호 수정(params: 이메일, 이름, 비밀번호)
    int modifyPassword(UserRequest params);

    // 멤버 정보 기입 및 수정
    int insertMemInfo(UserRequest params);
}
