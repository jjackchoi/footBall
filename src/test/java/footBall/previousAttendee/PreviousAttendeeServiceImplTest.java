package footBall.previousAttendee;

import footBall.domain.attendee.AttendeeServiceImpl;
import footBall.domain.previousAttendee.PreviousAttendeeServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PreviousAttendeeServiceImplTest {

    @Autowired
    PreviousAttendeeServiceImpl previousAttendeeService;
    @Autowired
    AttendeeServiceImpl attendeeService;

    @Test
    void getAllVote() {

        System.out.println(previousAttendeeService.getAllVote());
    }
}