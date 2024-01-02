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
    public List<UserResponse> userAllFind(){
        return sqlSession.selectList("userMapper.allFind");
    }
}
