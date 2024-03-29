package footBall;

import footBall.domain.attendee.AttendeeServiceImpl;
import groovy.util.logging.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class FootBallApplicationTests {

	@Autowired
	private AttendeeServiceImpl attendeeService;
	@Test
	void contextLoads() {
		// findDate 실행
		System.out.println(attendeeService.findDate());
		
	}

}
