package footBall;

import footBall.user.UserRequest;
import footBall.user.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class FootBallServiceTest {
    @Autowired
    UserServiceImpl userServiceImpl;

    @Test
    void registerTest(){
        UserRequest userRequest = new UserRequest();
        userRequest.setUserId("parkhyenwoo");
        userRequest.setUserPswd("1234");
        userRequest.setUserName("박현우");
        userRequest.setUserBirth("19960529");
        userRequest.setUserPhone("01012345678");
        userRequest.setUserAddress("서울특별시 금천구 독산동");
        System.out.println(userServiceImpl.userAllFind().size());
        userServiceImpl.userRegister(userRequest);
        System.out.println(userServiceImpl.userAllFind().size());
    }
}
