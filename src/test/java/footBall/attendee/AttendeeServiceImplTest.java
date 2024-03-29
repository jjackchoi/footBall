package footBall.attendee;

import footBall.domain.attendee.AttendeeServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AttendeeServiceImplTest {

    @Autowired
    private AttendeeServiceImpl attendeeService;
    @Test
    void dateCreate() {
//        AttendeeDto dto = new AttendeeDto();
//        LocalDateTime currentDate = LocalDateTime.now();
//        dto.setVoteDate(currentDate);
//        System.out.println(dto.toString());
//        attendeeService.createDate(dto);
    }
}