package footBall.user;

import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final SqlSession sqlSession;
    private final PasswordEncoder passwordEncoder;

    // 회원가입
    @Override
    @Transactional
    public int userRegister(UserRequest userRequest){
        userRequest.encodingPassword(passwordEncoder);
        return sqlSession.insert("UserMapper.register", userRequest);
    }

    // 유저 전체 조회
    @Override
    public List<UserResponse> getAllUser(){
        return sqlSession.selectList("UserMapper.getAllUser");
    }

    // 이메일로 유저 조회
    @Override
    public List<UserResponse> getUserByEmail(String email) {
        return sqlSession.selectList("UserMapper.getUserByEmail", email);
    }

    // 닉네임으로 유저 조회
    @Override
    public List<UserResponse> getUserByNickname(String nickname) {
        return sqlSession.selectList("UserMapper.getUserByNickname", nickname);
    }

    // 로그인
    @Override
    public int login(UserRequest dto) {
        // 1. 회원 정보 및 비밀번호 조회
        UserResponse userDto = sqlSession.selectOne("UserMapper.getUserByEmail", dto.getFbUserEmail());
        String encodedPassword = (userDto == null) ? "" : userDto.getFbUserPswd();

        // 2. 회원 정보 및 비밀번호 체크
        if (userDto == null || passwordEncoder.matches(dto.getFbUserPswd(), encodedPassword) == false){
            return 0;
        }

        return userDto.getFbUserId();
    }

    // 닉네임 조회
    @Override
    public String findNickname(int id) {
        return sqlSession.selectOne("UserMapper.findNickname",id);
    }

    // 정보조회
    @Override
    public UserResponse findOne(Integer id) {
        return sqlSession.selectOne("UserMapper.findOne",id);
    }
}
