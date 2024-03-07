package footBall;

import footBall.attendee.AttendeeDto;
import footBall.attendee.AttendeeServiceImpl;
import groovy.util.logging.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;

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
