package footBall;

import footBall.domain.user.UserRequest;
import footBall.domain.user.UserServiceImpl;
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
        userRequest.setFbUserEmail("parkhyenwoo@naver.com");
        userRequest.setFbUserPswd("1234");
        userRequest.setFbUserNickname("우리현우");
        userRequest.setFbUserName("박현우");
        userRequest.setFbUserBirth("19960529");
        userRequest.setFbUserPhone("01087147733");
        userRequest.setFbUserAddress("서울특별시 금천구 독산동");
        System.out.println(userServiceImpl.getAllUser().size());
        userServiceImpl.userRegister(userRequest);
        System.out.println(userRequest.toString());
        System.out.println(userServiceImpl.getAllUser().size());
    }
}
