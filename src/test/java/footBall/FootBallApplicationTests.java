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

	@Test
	void applyAbilityTest(){
		Double sm = 1.8;
		Double jm = 1.6;
		Double hw = 1.8;
		Double ib = 1.6;
		Double ch = 2.4;
		Double js = 1.8;
		Double kt = 1.8;
		Double bk = 1.6;
		Double sr = 1.4;
		Double kh = 2.2;
		Double gt = 2.4;
		Double ds = 2.8;
		Double os = 1.8;
		Double ggt = 1.4;
		Double jy = 1.6;
		Double jjs = 1.6;


	}

}
