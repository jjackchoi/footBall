package footBall.previousAttendee;

import footBall.attendee.AttendeeServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
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