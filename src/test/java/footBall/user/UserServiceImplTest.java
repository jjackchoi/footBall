package footBall.user;

import footBall.domain.user.UserRequest;
import footBall.domain.user.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserServiceImplTest {

    @Autowired
    private UserServiceImpl userService;
    @Test
    void getUserByEmail() {
    }

    @Test
    void login() {
        UserRequest dto = new UserRequest();
        dto.setFbUserEmail("chltmdals@naver.com");
        dto.setFbUserPswd("12345");
        int id = userService.login(dto);
        System.out.println(id);
    }
}