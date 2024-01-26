package footBall.user;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    SqlSession sqlSession;

    @Override
    public int userRegister(UserRequest userRequest){
        return sqlSession.insert("UserMapper.register", userRequest);
    }

    @Override
    public List<UserResponse> getAllUser(){
        return sqlSession.selectList("UserMapper.getAllUser");
    }

    @Override
    public UserResponse getUserByEmail(UserRequest dto) {
        return sqlSession.selectOne("UserMapper.getUserByEmail", dto);
    }

    @Override
    public Long login(UserRequest dto) {
        UserResponse userDto = sqlSession.selectOne("UserMapper.getUserByEmail", dto);
        if (userDto.getFbUserPswd().equals(dto.getFbUserPswd()))
            return userDto.getFbUserId();
        return null;
    }
}
