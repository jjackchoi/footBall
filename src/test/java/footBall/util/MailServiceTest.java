package footBall.util;

import footBall.common.mail.MailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MailServiceTest {

    @Autowired
    private MailService mailService;
    @Test
    void sendSimpleEmail() {
//        mailService.sendSimpleEmail();
    }

}