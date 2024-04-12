package footBall.freeBoard;

import footBall.domain.freeBoard.FreeBoardServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FreeBoardServiceImplTest {

    @Autowired
    private FreeBoardServiceImpl freeBoardService;
    @Test
    void findAll() {
//        System.out.println(freeBoardService.findAll());
    }
}