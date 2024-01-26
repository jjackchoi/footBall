package footBall.freeBoard;

import groovy.util.logging.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class FreeBoardServiceImplTest {

    @Autowired
    private FreeBoardServiceImpl freeBoardService;
    @Test
    void findAll() {
        System.out.println(freeBoardService.findAll());
    }
}