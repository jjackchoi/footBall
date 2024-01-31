package footBall.user;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    SqlSession sqlSession;

    // 회원가입
    @Override
    public int userRegister(UserRequest userRequest){
        return sqlSession.insert("UserMapper.register", userRequest);
    }

    // 유저 전체조회
    @Override
    public List<UserResponse> getAllUser(){
        return sqlSession.selectList("UserMapper.getAllUser");
    }

    // 닉네임으로 유저 조회
    @Override
    public List<UserResponse> getUserByNickname(String nickname) {
        return sqlSession.selectList("UserMapper.getUserByNickname", nickname);
    }

    // 로그인
    @Override
    public int login(UserRequest dto) {
        UserResponse userDto = sqlSession.selectOne("UserMapper.getUserByEmail", dto);
        if (userDto.getFbUserPswd().equals(dto.getFbUserPswd()))
            return userDto.getFbUserId();
        return 0;
    }
}
