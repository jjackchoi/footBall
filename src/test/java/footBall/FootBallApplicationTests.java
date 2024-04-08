package footBall;

import footBall.domain.attendee.AttendeeServiceImpl;
import footBall.domain.member.MemberDto;
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
		Long memberDribbleAbility = 3l;
		Long memberPassingAbility = 2l;
		Long memberDefendingAbility = 2l;
		Long memberStamina = 2l;
		Long memberFinishingAbility = 1l;

		MemberDto params = new MemberDto();
		params.setMemberDribbleAbility(3l);
		params.setMemberPassingAbility(2l);
		params.setMemberDefendingAbility(1l);
		params.setMemberStamina(4l);
		params.setMemberFinishingAbility(1l);
		params.calculateAbilityAverage();
		System.out.println(params.getMemberAbilityAvg());

	}

}
